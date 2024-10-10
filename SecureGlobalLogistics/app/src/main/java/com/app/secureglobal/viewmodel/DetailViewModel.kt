package com.app.secureglobal.viewmodel
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.app.secureglobal.R
import com.app.secureglobal.databinding.ActivityDetailBinding
import com.app.secureglobal.model.base.BaseViewModel
import com.app.secureglobal.model.getDocketForScan.GetDocketForScanResponse
import com.app.secureglobal.model.getDocketforPickupResponse.GetDocketForPickupResponse
import com.app.secureglobal.model.getSavePickupDataResponse.GetSavePickupResponse
import com.app.secureglobal.model.getSavePickupDataResponse.SaveDocketPickupData
import com.app.secureglobal.network.CallbackObserver
import com.app.secureglobal.network.Networking
import com.app.secureglobal.uttils.AppConstants
import com.app.secureglobal.uttils.Utility
import com.app.secureglobal.uttils.Utils
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailViewModel(@SuppressLint("StaticFieldLeak") private val context: Context, val binding: ActivityDetailBinding) : BaseViewModel(){


    private var isData = MutableLiveData<Boolean>()

    var senderNo: ObservableField<String> = ObservableField()
    var senderName: ObservableField<String> = ObservableField()
    var senderNumber: ObservableField<String> = ObservableField()

    // In Scan
    var growsWeight: ObservableField<String> = ObservableField()
    var growsWeightUnit: ObservableField<String> = ObservableField()
    var freight: ObservableField<String> = ObservableField()
    var insuranceCharge: ObservableField<String> = ObservableField()
    var shcCharge: ObservableField<String> = ObservableField()
    private var destinationBranchList: ArrayList<String>? = null


    private var docketId: ObservableField<Int> = ObservableField()
    var destinationBranch  = MutableLiveData<String>()


    // Scan Type
    var scanType: MutableLiveData<Int> = MutableLiveData()
    private var buyerTypeList: List<String>? = null

    fun init(context: Context) {
        isData.value = false


         buyerTypeList = context.resources.getStringArray(R.array.buyer_type)
         buyerTypeList = buyerTypeList.asList()
    }


    fun onSaveClicked() {
        if (scanType.value == AppConstants.InScan){
            validationForInScan()
        }
        else{
            validationForPickup()
        }

    }

    private fun validationForInScan() {
        if (!destinationBranchList.isNullOrEmpty()){
            val index = destinationBranchList!!.indexOfFirst { it == destinationBranch.value }
            Log.e("BranchId",index.toString())
        }
    }

    private fun validationForPickup() {
        if (senderNo.get().toString() == "" || senderNo.get() == null) {
            Utils().showSnackBar(context, "Please Enter Seal No.", binding.constraintLayout)
        }
        else if (senderName.get().toString() == "" || senderName.get() == null) {
            Utils().showSnackBar(context, "Please Enter Sender Name", binding.constraintLayout)
        }
        else if (senderNumber.get().toString() == "" || senderNumber.get() == null) {
            Utils().showSnackBar(context, "Please Enter Sender Number", binding.constraintLayout)
        }else{
            savePickupDetail()
        }
    }

    private fun savePickupDetail() {

        if (scanType.value == AppConstants.InScan){
            saveInScanData()
        }else{
            saveInPickupData()
        }

    }

    private fun saveInPickupData() {
        val savePickupResponse = SaveDocketPickupData()
        savePickupResponse.setDocketId(docketId.get())
        savePickupResponse.setSglbagNo(senderNo.get().toString())
        savePickupResponse.setSenderName(senderName.get().toString())
        savePickupResponse.setSenderMobileNo(senderNumber.get().toString())


        val gson = Gson()
        val json = gson.toJson(savePickupResponse)
        Log.e("Json", json)

        if (Utility.isNetworkConnected(context)) {
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getSavePickupResponse(savePickupResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetSavePickupResponse>() {
                    override fun onSuccess(response: GetSavePickupResponse) {
                        isLoading.postValue(false)
                    }
                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }
                    override fun onNext(t: GetSavePickupResponse) {
                        isLoading.postValue(false)
                        if (t.getStatusCode() == 200) {
                            Utils().showSnackBar(context, t.getMessage().toString(), binding.constraintLayout)
                        } else {
                            Utils().showSnackBar(context, t.getMessage().toString(), binding.constraintLayout)
                        }
                    }
                })
        } else {
            Utils().showSnackBar(context, context.getString(R.string.nointernetconnection).toString(), binding.constraintLayout)
        }
    }

    private fun saveInScanData() {

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
                            setPickupData(t)
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

    fun getDocketForScanResult(docketNumber: String) {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getDocketForScanResponse(docketNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetDocketForScanResponse>() {
                    override fun onSuccess(response: GetDocketForScanResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())
                        Utils().showToast(context,"Authentication token has expired")
                        //  redirectToLogin()
                    }

                    override fun onNext(t: GetDocketForScanResponse) {
                        isLoading.postValue(false)
                        Log.e("Status",t.getStatusCode().toString())
                        if(t.getStatusCode() == 200){
                            setScanData(t)
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

    private fun setScanData(t: GetDocketForScanResponse) {
        if (t.getData() != null){
            docketId.set(t.getData()!!.getDocketId())
            senderNumber.set(Utility.getNullToBlankString(t.getData()!!.getBuyerMobileNo()!!))
            growsWeight.set(Utility.getNullToBlankString(t.getData()!!.getGrWt().toString()))
            growsWeightUnit.set(Utility.getNullToBlankString(t.getData()!!.getGrWtUnit().toString()))
            freight.set(Utility.getNullToBlankString(t.getData()!!.getFreight().toString()))

            destinationBranchList = ArrayList()

            if (!t.getData()!!.getBranchList().isNullOrEmpty()){
                for (i in t.getData()!!.getBranchList()!!){
                    destinationBranchList!!.add(i.text)
                }
            }

            binding.spnBranch.setListAdapter(destinationBranchList)
        }
    }

    private fun setPickupData(t: GetDocketForPickupResponse) {

        if (t.getData() != null){

            docketId.set(t.getData()!!.getDocketId())

            binding.pickupDocket.txtShipperName.text = Utility.getNullToBlankString(t.getData()!!.getShipperName().toString())
            binding.pickupDocket.txtShipperAddress.text = Utility.getNullToBlankString(t.getData()!!.getShipperAddress().toString())
            binding.pickupDocket.txtShipperNumber.text = Utility.getNullToBlankString(t.getData()!!.getShipperMobileNo().toString())
            binding.pickupDocket.txtgstnumber.text = Utility.getNullToBlankString(t.getData()!!.getShipperGstin().toString())

            binding.pickupDocket.txtConsigneeName.text = Utility.getNullToBlankString(t.getData()!!.getBuyerName().toString())
            binding.pickupDocket.txtConsigneeAddress.text = Utility.getNullToBlankString(t.getData()!!.getBuyerAddress().toString())
            binding.pickupDocket.txtConsigneeMobileNumber.text = Utility.getNullToBlankString(t.getData()!!.getBuyerMobileNo().toString())
            binding.pickupDocket.txtConsigneeGSTNumber.text = Utility.getNullToBlankString(t.getData()!!.getBuyerGstin().toString())

            binding.pickupDocket.txtOrigin.text = Utility.getNullToBlankString(t.getData()!!.getShipperCity().toString())
            binding.pickupDocket.txtDestination.text = Utility.getNullToBlankString(t.getData()!!.getBuyerCity().toString())
        }
        else{
            Utils().showToast(context,"No Data Found!")
        }
    }

}