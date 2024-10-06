package com.app.secureglobal.view.menu

import android.R.attr
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.app.secureglobal.R
import com.app.secureglobal.databinding.DashboardMenuFragmentBinding
import com.app.secureglobal.model.dashboard.getDashboardApiResponse.GetMobileDashboardDetailDto
import com.app.secureglobal.uttils.Utils
import com.app.secureglobal.view.base.BaseFragment
import com.app.secureglobal.viewmodel.DashboardMenuViewModel
import com.google.zxing.integration.android.IntentIntegrator


class DashboardMenuFragment: BaseFragment()  {

    private var _binding: DashboardMenuFragmentBinding? = null

    private val binding get() = _binding!!

    private val dashboardMenuViewModel by lazy { DashboardMenuViewModel(activity as Context,binding,this@DashboardMenuFragment) }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DashboardMenuFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = dashboardMenuViewModel
        binding.lifecycleOwner = this
        dashboardMenuViewModel.init()

        dashboardMenuViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }
        return binding.root
    }

    fun  redirectToDetailScreen(getDashboard: GetMobileDashboardDetailDto) {
        if (!getDashboard.getAdditionalCaption().isNullOrEmpty() && getDashboard.getAdditionalCaption().equals("0")){
            context?.let { Utils().showToast(it,"No Data Found!") }
        }else {
            val intentIntegrator = IntentIntegrator(requireActivity())
            intentIntegrator.setPrompt("Scan a barcode or QR Code")
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.initiateScan()
        }
    }

    fun  redirectToWebView(rcuType: String?) {
        val bundle = Bundle()
        bundle.putString("webURL", rcuType)
        findNavController().navigate(R.id.action_dashboardMenuFragment_to_webViewFragment,bundle)

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_nav_menup)
        (context as DashboardActivity).setTitle()
    }
}