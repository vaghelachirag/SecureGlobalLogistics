package com.app.secureglobal.viewmodel
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.app.secureglobal.R
import com.app.secureglobal.databinding.ActivityDetailBinding
import com.app.secureglobal.uttils.Utils
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDetailData
import com.app.secureglobal.network.CallbackObserver
import com.app.secureglobal.model.base.BaseViewModel
import com.app.secureglobal.model.getDocketforPickupResponse.GetDocketForPickupResponse
import com.app.secureglobal.network.Networking
import com.app.secureglobal.uttils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val context: Context,  val binding: ActivityDetailBinding) : BaseViewModel(){


  //  lateinit var getVerificationDetailData : GetVerificationDetailData

    var isData = MutableLiveData<Boolean>()

    var getVerificationDetailData: MutableLiveData<GetVerificationDetailData> = MutableLiveData()


    var shipperPersonName : ObservableField<String> = ObservableField()


    fun init(context: Context) {
       isData.value = false
    }


    public fun getDocketForPickupResult(docketNumber: String) {
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
                            shipperPersonName.set(t.getData()!!.getShipperName().toString())
                            binding.pickupDocket.txtVerificationForHeader.text = t.getData()!!.getShipperName().toString()
                            binding.pickupDocket.txtAddress.text = t.getData()!!.getShipperAddress().toString()
                            binding.pickupDocket.txtShipperNumber.text = t.getData()!!.getShipperMobileNo().toString()
                            binding.pickupDocket.txtgstnumber.text = t.getData()!!.getShipperGstin().toString()
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