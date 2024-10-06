package com.app.secureglobal.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.secureglobal.uttils.Utils
import com.app.secureglobal.R
import com.app.secureglobal.model.base.BaseViewModel
import com.app.secureglobal.model.getMenuWebUrlResponse.GetMenuURLResponse
import com.app.secureglobal.network.CallbackObserver
import com.app.secureglobal.network.Networking
import com.app.secureglobal.uttils.Session
import com.app.secureglobal.uttils.Utility
import com.app.secureglobal.view.menu.DashboardActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WebViewViewModel(private val context: Context): BaseViewModel() {
    var webViewURL = MutableLiveData<String>()

    // Session Manager
    var session = Session(context)

    fun init(context: Context, menuId: String) {
         webViewURL.value = ""
        callGetWebURLApiResponse(menuId)
    }

    private fun callGetWebURLApiResponse(menuId: String) {

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getMenuURLResponse(menuId,
                    DashboardActivity.currentLat.toString(),
                    DashboardActivity.currentLong.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetMenuURLResponse>() {
                    override fun onSuccess(response: GetMenuURLResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }

                    override fun onNext(t: GetMenuURLResponse) {
                        Log.e("Status",t.getStatusCode().toString())
                        isLoading.postValue(false)
                        if(t.getStatusCode() == 200){
                            if(t.getData() != null){
                               webViewURL.value = t.getData()!!.getUrl()
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
}