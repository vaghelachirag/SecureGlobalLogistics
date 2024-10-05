package com.squmish.rcuapp.view.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.squmish.rcuapp.R
import com.squmish.rcuapp.databinding.FragmentFinalSubmitBinding
import com.squmish.rcuapp.interfaces.FragmentLifecycleInterface
import com.squmish.rcuapp.model.getverificationDetailResponse.GetVerificationDetailData
import com.squmish.rcuapp.uttils.AppConstants
import com.squmish.rcuapp.uttils.Utility.Companion.setAllEnabled
import com.squmish.rcuapp.view.base.BaseFragment
import com.squmish.rcuapp.view.menu.DashboardActivity
import com.squmish.rcuapp.viewmodel.verificationDetail.FinalSubmitViewModel

class FragmentFinalSubmit  : BaseFragment(), FragmentLifecycleInterface {

    private var _binding: FragmentFinalSubmitBinding? = null

    // This property is only valid between onCreateView and
    private val binding get() = _binding!!
    private val finalSubmitViewModel by lazy { context?.let { FinalSubmitViewModel(it,binding) } }

    var data : String = ""



    companion object {
        fun newInstance(selectedData: GetVerificationDetailData?): FragmentFinalSubmit {
            val bundle = Bundle()
            //  bundle.putSerializable(DATA, selectedData)
            val fragmentFinalSubmit = FragmentFinalSubmit()
            fragmentFinalSubmit.arguments = bundle
            return fragmentFinalSubmit
        }
    }

    fun redirectToDashboardScreen(){
        findNavController().navigate(R.id.dashboardFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFinalSubmitBinding.inflate(inflater, container, false)

        binding.viewModel = finalSubmitViewModel
        binding.lifecycleOwner = this
        finalSubmitViewModel!!.init(context)
        setView()
        return binding.root
    }

    private fun setView() {
        if(ActivityDetail.selectedData!!.getStatus() != null){
            if(ActivityDetail.selectedData!!.getStatus() == AppConstants.statusPending){
              //  binding.constraintLayout.forEach { child -> child.setAllEnabled(false) }
            }
            else{
                binding.constraintLayout.forEach { child -> child.setAllEnabled(true) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finalSubmitViewModel!!.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPauseFragment() {

    }
    override fun onResumeFragment(s: String?) {
        Log.e("OnResume","Final Submit")

    }

}