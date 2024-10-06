package com.app.secureglobal.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.secureglobal.databinding.ItemDashboardBinding
import com.app.secureglobal.model.pendingRequest.GetPendingRequestData
import com.app.secureglobal.viewmodel.DashboardViewModel

class DashboardViewHolder (val binding: ItemDashboardBinding, val viewModel: DashboardViewModel) :  RecyclerView.ViewHolder(binding.root){


    fun bind(data: GetPendingRequestData) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
        binding.viewmodel = viewModel
    }
}