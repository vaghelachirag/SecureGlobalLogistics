package com.app.secureglobal.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.secureglobal.uttils.Utils
import com.app.secureglobal.view.adapter.DashboardAdapter
import com.app.secureglobal.view.menu.DashboardFragment
import com.app.secureglobal.R
import com.app.secureglobal.databinding.DashboardFragmentBinding
import com.app.secureglobal.interfaces.OnItemSelected
import com.app.secureglobal.model.base.BaseViewModel
import com.app.secureglobal.model.getUserProfileData.GetUserProfileResponse
import com.app.secureglobal.model.getmasterData.GetMasterApiResponse
import com.app.secureglobal.model.pendingRequest.GetPendingRequestData
import com.app.secureglobal.model.pendingRequest.GetPendingRequestResponse
import com.app.secureglobal.network.CallbackObserver
import com.app.secureglobal.network.Networking
import com.app.secureglobal.room.InitDb
import com.app.secureglobal.room.dao.MasterDataDao
import com.app.secureglobal.uttils.Session
import com.app.secureglobal.uttils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DashboardViewModel(val context: Context, val dashboardFragment: DashboardFragment, val binding: DashboardFragmentBinding) : BaseViewModel(){

    var verificationList: ArrayList<GetPendingRequestData> = ArrayList()
    private var dashboardAdapter: DashboardAdapter? = null

    var totalVerification : MutableLiveData<Int> = MutableLiveData()

    fun getDashboardListAdapter(): DashboardAdapter? = dashboardAdapter

    private var masterDataDao: MasterDataDao? = null

    // Session Manager
    var session = Session(context)

    var rcuType = ""

    fun init(context: Context) {
        totalVerification.value = 0
        masterDataDao = InitDb.appDatabase.getMasterData()
        rcuType =  (dashboardFragment).requireArguments().getString("RcuType").toString()
        getPendingRequest()
    }

    @SuppressLint("HardwareIds")
    fun getPendingRequest() {

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getPendingRequest(rcuType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetPendingRequestResponse>() {
                    override fun onSuccess(response: GetPendingRequestResponse) {
                        isLoading.postValue(false)
                        binding.refreshLayout.isRefreshing = false
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        binding.refreshLayout.isRefreshing = false
                        Log.e("Status",code.toString())
                    }

                    override fun onNext(t: GetPendingRequestResponse) {
                        Log.e("Status",t.getStatusCode().toString())
                        isLoading.postValue(false)
                        binding.refreshLayout.isRefreshing = false
                        if(t.getStatusCode() == 200){
                          verificationList.clear()
                          if(t.getData() != null){
                             verificationList = t.getData()!!
                              totalVerification.value = verificationList.size
                              setVerificationAdapter()
                             if (session.getIsFirstTimeKey()){
                                 CoroutineScope(Dispatchers.IO).launch {
                                     if (masterDataDao!!.getMasterDataList().size > 0){
                                         Log.e("Master",masterDataDao!!.getMasterDataList().size.toString())
                                      }
                                     else{
                                         getMasterDataApi()
                                     }
                                 }
                             }
                          }
                        }else{
                            isLoading.postValue(false)
                            Utils().showToast(context,t.getMessage().toString())
                            Log.e("StatusCode",t.getStatus().toString())
                        }
                        Log.e("StatusCode",t.getStatus().toString())
                    }

                })
        }else{

            isLoading.postValue(false)
            Utils().showToast(context,context.getString(R.string.nointernetconnection).toString())
        }
    }

   // Get Master Data Api
    private fun getMasterDataApi() {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getMasterApiData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetMasterApiResponse>() {
                    override fun onSuccess(response: GetMasterApiResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }

                    override fun onNext(t: GetMasterApiResponse) {
                        Log.e("Status",t.getStatusCode().toString())
                        isLoading.postValue(false)
                        if(t.getStatusCode() == 200){
                            if(t.getData() != null){
                                CoroutineScope(Dispatchers.IO).launch {
                                    //getUserProfileData()
                                    masterDataDao!!.insertAll(t.getData())
                                }
                            }
                        }else{
                            Utils().showToast(context,t.getMessage().toString())
                        }
                        Log.e("StatusCode",t.getStatus().toString())
                    }

                })
        }else{
            Utils().showToast(context,context.getString(R.string.nointernetconnection).toString())
        }
    }

    private fun getUserProfileData() {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getUserProfileData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetUserProfileResponse>() {
                    override fun onSuccess(response: GetUserProfileResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }

                    override fun onNext(t: GetUserProfileResponse) {
                        Log.e("Status",t.getStatusCode().toString())
                        isLoading.postValue(false)
                        if(t.getStatusCode() == 200){
                            if(t.getData() != null){
                                t.getData()!!.getUserName()?.let { session.storeUserNameKey(it) }
                                t.getData()!!.getProfilePicture()?.let { session.storeUserProfileImageKey(it) }
                                session.storeIsFirstTimeKey(false)
                            }
                        }else{
                            Utils().showToast(context,t.getMessage().toString())
                        }
                        Log.e("StatusCode",t.getStatus().toString())
                    }

                })
        }else{
            Utils().showToast(context,context.getString(R.string.nointernetconnection).toString())
        }
    }

    private fun setVerificationAdapter() {
        dashboardAdapter =  DashboardAdapter(context,verificationList, this, object :
            OnItemSelected<GetPendingRequestData> {
            override fun onItemSelected(t: GetPendingRequestData?, position: Int) {
                Log.e("OnItem", "OnItem$position")
                dashboardFragment.redirectToDetailScreen(verificationList[position])
            }
        })
        binding.rvDashboard.setLayoutManager(LinearLayoutManager(context))
        binding.rvDashboard.setAdapter(dashboardAdapter)
    }

}