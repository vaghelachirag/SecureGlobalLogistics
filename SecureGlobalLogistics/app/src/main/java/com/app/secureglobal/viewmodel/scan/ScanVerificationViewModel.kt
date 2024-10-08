package com.app.secureglobal.viewmodel.scan

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import com.app.secureglobal.R
import com.app.secureglobal.databinding.ActivityDashboardBinding
import com.app.secureglobal.model.base.BaseViewModel
import com.app.secureglobal.model.getDocketforPickupResponse.GetDocketForPickupResponse
import com.app.secureglobal.model.getSavePickupDataResponse.GetSavePickupResponse
import com.app.secureglobal.network.CallbackObserver
import com.app.secureglobal.network.Networking
import com.app.secureglobal.uttils.Utility
import com.app.secureglobal.uttils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ScanVerificationViewModel(val context: Context, val binding: ActivityDashboardBinding) : BaseViewModel()  {

    // Address Detail Params
    var buyerName: ObservableField<String> = ObservableField()


    fun init(context: Context) {

    }

    // Get Docket For Pickup
    private fun getDocketForPickupResult(docketNumber: String) {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getDocketForPickupResponse(docketNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetDocketForPickupResponse>() {
                    override fun onSuccess(response: GetDocketForPickupResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())
                        Utils().showToast(context,"Authentication token has expired")
                    }

                    override fun onNext(t: GetDocketForPickupResponse) {
                        isLoading.postValue(false)
                        Log.e("Status",t.getStatusCode().toString())
                        if(t.getStatusCode() == 200){

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
}