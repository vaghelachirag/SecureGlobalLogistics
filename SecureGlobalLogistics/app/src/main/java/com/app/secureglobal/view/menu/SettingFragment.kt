package com.app.secureglobal.view.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.secureglobal.databinding.SettingFragmentBinding
import com.app.secureglobal.view.base.BaseFragment
import com.app.secureglobal.viewmodel.SettingViewModel

class SettingFragment : BaseFragment()  {

    private var _binding: SettingFragmentBinding? = null

    private val binding get() = _binding!!
    private val settingViewModel by lazy { SettingViewModel(activity as Context,this@SettingFragment) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = SettingFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = settingViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}