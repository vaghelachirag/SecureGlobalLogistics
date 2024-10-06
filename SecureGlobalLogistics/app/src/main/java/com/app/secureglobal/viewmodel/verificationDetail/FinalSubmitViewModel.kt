package com.app.secureglobal.viewmodel.verificationDetail

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.app.secureglobal.uttils.Utils
import com.app.secureglobal.R
import com.app.secureglobal.databinding.FragmentFinalSubmitBinding
import com.app.secureglobal.model.base.BaseViewModel
import com.app.secureglobal.model.finalSubmission.GetFinalSubmissionApiResponse
import com.app.secureglobal.model.finalSubmission.SaveFinalSubmissionData
import com.app.secureglobal.network.CallbackObserver
import com.app.secureglobal.network.Networking
import com.app.secureglobal.room.InitDb
import com.app.secureglobal.room.dao.MasterDataDao
import com.app.secureglobal.uttils.AppConstants
import com.app.secureglobal.uttils.Utility
import com.app.secureglobal.view.detail.ActivityDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FinalSubmitViewModel(private val context: Context,private  val binding: FragmentFinalSubmitBinding): BaseViewModel(){


    var selectedReasonPosition = MutableLiveData<Int>()
    var selectedItemPosition: Int = 0

    // Room Database
    private var masterDataDao: MasterDataDao? = null

    private var finalSubmissionList: List<String>? = null
    private var finalSubmissionSpinnerAdapter: ArrayAdapter<String?>? = null

    var edtRemark: ObservableField<String> = ObservableField()


    fun init(context: Context?) {
        masterDataDao = InitDb.appDatabase.getMasterData()
        getDataFromMasterData()
    }

    public fun onSaveClicked(){
       if (edtRemark.get().isNullOrEmpty()) {
            Utils().showSnackBar(context, "Please Enter Remark", binding.constraintLayout)
        }else{
            callFinalSubmitApi()
        }
    }

    private fun callFinalSubmitApi() {
        val saveFinalSubmit: SaveFinalSubmissionData = SaveFinalSubmissionData()
        saveFinalSubmit.setFIStatus(binding.spFinalSubmission.text.toString())
        saveFinalSubmit.setFIRequestId(AppConstants.verificationId)
        saveFinalSubmit.setRemarks(edtRemark.get())

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getSaveFinalSubmissionResponse(saveFinalSubmit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetFinalSubmissionApiResponse>() {
                    override fun onSuccess(response: GetFinalSubmissionApiResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }

                    override fun onNext(t: GetFinalSubmissionApiResponse) {
                        Log.e("Status",t.getStatusCode().toString())
                        isLoading.postValue(false)

                        if(t.getStatusCode() == 200){
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                            (context as ActivityDetail).finish()
                        }else{
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",t.getStatus().toString())
                    }
                })
        }else{
            Utils().showSnackBar(context,context.getString(R.string.nointernetconnection).toString(),binding.constraintLayout)
        }
    }

    private fun getDataFromMasterData() {
        CoroutineScope(Dispatchers.IO).launch {
            finalSubmissionList = masterDataDao!!.getDataByKeyName(AppConstants.finalSubmission)

            finalSubmissionSpinnerAdapter = ArrayAdapter<String?>(context, android.R.layout.simple_spinner_item, finalSubmissionList!!)
            finalSubmissionSpinnerAdapter?.setDropDownViewResource(R.layout.custom_spinner_item)
            binding.spFinalSubmission.setListAdapter(finalSubmissionList)

         //   binding.spFinalSubmission.adapter = finalSubmissionSpinnerAdapter
        }
    }

}