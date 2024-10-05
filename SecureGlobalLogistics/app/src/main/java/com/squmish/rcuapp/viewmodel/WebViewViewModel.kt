package com.squmish.rcuapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.squmish.rcuapp.uttils.Utils
import com.squmish.rcuapp.R
import com.squmish.rcuapp.model.base.BaseViewModel
import com.squmish.rcuapp.model.getMenuWebUrlResponse.GetMenuURLResponse
import com.squmish.rcuapp.network.CallbackObserver
import com.squmish.rcuapp.network.Networking
import com.squmish.rcuapp.uttils.Session
import com.squmish.rcuapp.uttils.Utility
import com.squmish.rcuapp.view.menu.DashboardActivity
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
                .getMenuURLResponse(menuId,DashboardActivity.currentLat.toString(),DashboardActivity.currentLong.toString())
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