package com.app.secureglobal.view.scan

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.viewpager.widget.ViewPager
import com.app.secureglobal.databinding.ActivityDetailBinding
import com.app.secureglobal.databinding.ActivityScanBinding
import com.app.secureglobal.interfaces.FragmentLifecycleInterface
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDetailData
import com.app.secureglobal.uttils.AppConstants
import com.app.secureglobal.uttils.Utils
import com.app.secureglobal.view.adapter.VerificationDetailViewPagerAdapter
import com.app.secureglobal.view.base.BaseActivity
import com.app.secureglobal.view.detail.fiRequest.FragmentRCOVerification
import com.app.secureglobal.viewmodel.DetailViewModel


open class ActivityScan  : BaseActivity()  {

    private lateinit var binding: ActivityScanBinding

    @SuppressLint("DiscouragedPrivateApi", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)

    }

    private fun setAction() {

    }

    @SuppressLint("SetTextI18n")
    private fun setActionBarHeader() {

    }

    override fun onResume() {
        super.onResume()
        //  detailViewModel.init(this)
        Log.e("OnResume","OnResume")
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setView() {

    }

}