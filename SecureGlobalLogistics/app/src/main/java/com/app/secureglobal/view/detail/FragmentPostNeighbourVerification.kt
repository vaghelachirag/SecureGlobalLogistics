package com.app.secureglobal.view.detail

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import com.app.secureglobal.databinding.FragmentPostNeighbourVerificationBinding
import com.app.secureglobal.interfaces.FragmentLifecycleInterface
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDetailData
import com.app.secureglobal.uttils.AppConstants
import com.app.secureglobal.uttils.Utility.Companion.setAllEnabled
import com.app.secureglobal.view.base.BaseFragment
import com.app.secureglobal.viewmodel.verificationDetail.PostNeighbourVerificationViewModel


class FragmentPostNeighbourVerification  : BaseFragment(), FragmentLifecycleInterface {


    private var _binding: FragmentPostNeighbourVerificationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val postNeighbourVerificationViewModel by lazy { PostNeighbourVerificationViewModel(context as Activity,binding) }

    var data : String = ""



    companion object {
        fun newInstance(selectedData: GetVerificationDetailData?): FragmentPostNeighbourVerification {
            val bundle = Bundle()
            val fragmentPostNeighbourVerification = FragmentPostNeighbourVerification()
            fragmentPostNeighbourVerification.arguments = bundle
            return fragmentPostNeighbourVerification
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPostNeighbourVerificationBinding.inflate(inflater, container, false)

        binding.viewModel = postNeighbourVerificationViewModel
        binding.lifecycleOwner = this
        context?.let { postNeighbourVerificationViewModel.init(it) }
//      basicInformationModel.init(context, FragmentDetail.selectedData!!)

        postNeighbourVerificationViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }
        setView()
        Log.e("OnCrete","PhotoNeighbour")
        return binding.root
    }

    private fun setView() {
        postNeighbourVerificationViewModel.isNeighbourReconised.observeForever {
            if (it){
                binding.inpReason.visibility = View.GONE
                binding.edtReason.setText(buildString {})
            }
            else{
                binding.inpReason.visibility = View.VISIBLE
            }
        }


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
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPauseFragment() {

    }

    override fun onResumeFragment(s: String?) {
        Log.e("OnResume","Post Neighbour")

    }

}