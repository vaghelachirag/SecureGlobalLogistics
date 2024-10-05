package com.squmish.rcuapp.viewmodel

import DashboardSelectionAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.squmish.rcuapp.R
import com.squmish.rcuapp.databinding.DashboardMenuFragmentBinding
import com.squmish.rcuapp.interfaces.OnItemSelected
import com.squmish.rcuapp.model.base.BaseViewModel
import com.squmish.rcuapp.model.dashboard.getDashboardApiResponse.GetDashboardApiResponse
import com.squmish.rcuapp.model.dashboard.getDashboardApiResponse.GetMobileDashboardDetailDto
import com.squmish.rcuapp.network.CallbackObserver
import com.squmish.rcuapp.network.Networking
import com.squmish.rcuapp.uttils.Utility
import com.squmish.rcuapp.uttils.Utils
import com.squmish.rcuapp.view.menu.DashboardActivity
import com.squmish.rcuapp.view.menu.DashboardMenuFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DashboardMenuViewModel(
    val context: Context,
    val binding: DashboardMenuFragmentBinding,
    val dashboardMenuFragment: DashboardMenuFragment
) : BaseViewModel() {

    var dashboardMenuList: ArrayList<GetMobileDashboardDetailDto> = ArrayList()
    private var dashboardAdapter: DashboardSelectionAdapter? = null


    fun init() {
        getDashboardMenu()
    }

    @SuppressLint("HardwareIds")
    fun getDashboardMenu() {

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getDashboardMenuResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetDashboardApiResponse>() {
                    override fun onSuccess(response: GetDashboardApiResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())
                        Utils().showToast(context,"Authentication token has expired")
                        (context as DashboardActivity).redirectToLogin()
                    }

                    override fun onNext(t: GetDashboardApiResponse) {
                        isLoading.postValue(false)
                        Log.e("Status",t.getStatusCode().toString())
                        if(t.getStatusCode() == 200){
                            if(t.getData() != null){
                                dashboardMenuList = t.getData()!!.getMobileDashboardDetailDto()!!
                                setDashboardMenuAdapter()
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
    private fun setDashboardMenuAdapter() {
        dashboardAdapter =  DashboardSelectionAdapter(context,dashboardMenuList, this, object :
            OnItemSelected<GetMobileDashboardDetailDto> {
            override fun onItemSelected(getdashboard: GetMobileDashboardDetailDto?, position: Int) {
                Log.e("OnItem", "OnItem$position")
                if (getdashboard!!.getIsWebView() == true){
                  dashboardMenuFragment.redirectToWebView(getdashboard.getButtonId().toString())
                }
                else{
                    dashboardMenuFragment.redirectToDetailScreen(getdashboard)
                }
            }
        })
        binding.rvDashboardMenu.layoutManager = GridLayoutManager(context,2)
      //  binding.rvDashboardMenu.setLayoutManager(Grid(context))
        binding.rvDashboardMenu.setAdapter(dashboardAdapter)
    }
}