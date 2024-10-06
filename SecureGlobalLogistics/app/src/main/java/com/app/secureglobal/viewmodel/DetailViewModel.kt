package com.app.secureglobal.viewmodel
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.secureglobal.uttils.Utils
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDetailData
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDetailResponse
import com.app.secureglobal.network.CallbackObserver
import com.app.secureglobal.model.base.BaseViewModel
import com.app.secureglobal.network.Networking
import com.app.secureglobal.uttils.AppConstants
import com.app.secureglobal.uttils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val context: Context) : BaseViewModel(){


  //  lateinit var getVerificationDetailData : GetVerificationDetailData

    var isData = MutableLiveData<Boolean>()

    var getVerificationDetailData: MutableLiveData<GetVerificationDetailData> = MutableLiveData()

    fun init(context: Context) {
       isData.value = false
     getVerificationRequestDetail()
    }

    private fun getVerificationRequestDetail() {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getVerificationRequestDetail(AppConstants.verificationId.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetVerificationDetailResponse>() {
                    override fun onSuccess(response: GetVerificationDetailResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }

                    override fun onNext(t: GetVerificationDetailResponse) {
                        Log.e("Status",t.getStatusCode().toString())
                        isLoading.postValue(false)
                        if(t.getStatusCode() == 200){
                           if(t.getData() != null){
                               isData.value = true
                               getVerificationDetailData.postValue(t.getData())
                           }
                        }else{
                            Utils().showToast(context,t.getMessage().toString())
                        }
                    }
                })
        }else{

        }

    }

}