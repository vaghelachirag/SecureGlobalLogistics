package com.app.secureglobal.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import com.app.secureglobal.R
import com.app.secureglobal.databinding.FragmentFinalSubmitBinding
import com.app.secureglobal.interfaces.FragmentLifecycleInterface
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDetailData
import com.app.secureglobal.uttils.AppConstants
import com.app.secureglobal.uttils.Utility.Companion.setAllEnabled
import com.app.secureglobal.view.base.BaseFragment
import com.app.secureglobal.viewmodel.verificationDetail.FinalSubmitViewModel

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