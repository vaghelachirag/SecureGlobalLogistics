package com.app.secureglobal.view.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.app.secureglobal.MainActivity
import com.app.secureglobal.R
import com.app.secureglobal.databinding.ActivityDetailBinding
import com.app.secureglobal.model.getDocketForScan.GetDocketForScanResponse
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDetailData
import com.app.secureglobal.network.CallbackObserver
import com.app.secureglobal.network.Networking
import com.app.secureglobal.room.InitDb
import com.app.secureglobal.uttils.AppConstants
import com.app.secureglobal.uttils.Utility
import com.app.secureglobal.uttils.Utils
import com.app.secureglobal.view.base.BaseActivity
import com.app.secureglobal.viewmodel.DetailViewModel
import com.google.zxing.integration.android.IntentIntegrator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class ActivityDetail  : BaseActivity()  {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel by lazy { DetailViewModel(this,binding) }


    var scanType: Int = 0


    @SuppressLint("DiscouragedPrivateApi", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        scanType = bundle!!.getInt("ScanType")
        Log.e("ScanType",scanType.toString())

     /*   val intentIntegrator = IntentIntegrator(this)
        intentIntegrator.setPrompt("Scan a barcode or QR Code")
        intentIntegrator.setOrientationLocked(true)
        intentIntegrator.initiateScan()*/

        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        binding.lifecycleOwner = this
        binding.pickupDetail = detailViewModel
        detailViewModel.init(this)

        setView()
        setActionBarHeader()
        setAction()


        detailViewModel.getDocketForPickupResult("TEST5555")

    }

    private fun setAction() {
        binding.layoutDetail.ivBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setActionBarHeader() {
        binding.layoutDetail.tvTitle.text = "Verification Detail"
    }

    companion object {
        public  var selectedData: GetVerificationDetailData? = null
        public  var useraddress : String = ""
    }

    override fun onResume() {
        super.onResume()
        //  detailViewModel.init(this)
        Log.e("OnResume","OnResume")
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setView() {
        binding.lifecycleOwner = this

        detailViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) showProgressbar()
            else if (!isLoading) hideProgressbar()
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("Scan","Scan")

            val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

            // if the intentResult is null then
            // toast a message as "cancelled"
            if (intentResult != null) {
                if (intentResult.contents == null) {
                    Toast.makeText(baseContext, "Cancelled", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Log.e("Scan",intentResult.contents)
                    if (scanType == AppConstants.InScan){
                        getDocketForScanResult(intentResult.contents)
                    }else{
                        detailViewModel.getDocketForPickupResult(intentResult.contents)
                      //  getDocketForPickupResult(intentResult.contents)
                    }

                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
    }


    private fun getDocketForScanResult(docketNumber: String) {
        if (Utility.isNetworkConnected(this)){
            showProgressbar()
            Networking.with(this)
                .getServices()
                .getDocketForScanResponse(docketNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetDocketForScanResponse>() {
                    override fun onSuccess(response: GetDocketForScanResponse) {
                        hideProgressbar()
                    }

                    override fun onFailed(code: Int, message: String) {
                        hideProgressbar()
                        Log.e("Status",code.toString())
                        Utils().showToast(this@ActivityDetail,"Authentication token has expired")
                        redirectToLogin()
                    }

                    override fun onNext(t: GetDocketForScanResponse) {
                        hideProgressbar()
                        Log.e("Status",t.getStatusCode().toString())
                        if(t.getStatusCode() == 200){
                            Utils().showToast(this@ActivityDetail,t.getMessage().toString())
                        }else{
                            Utils().showToast(this@ActivityDetail,t.getMessage().toString())
                            finish()
                        }
                        Log.e("StatusCode",t.getStatus().toString())
                    }

                })
        }else{
            Utils().showToast(this@ActivityDetail,getString(R.string.nointernetconnection).toString())
        }
    }

    fun redirectToLogin(){
        Log.e("Login","Login")
        CoroutineScope(Dispatchers.IO).launch {
            InitDb.appDatabase.clearAllTables()
        }
        val intentLogin = Intent(this,MainActivity()::class.java)
        startActivity(intentLogin)
        finish()
    }

}