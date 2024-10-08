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
    }

}