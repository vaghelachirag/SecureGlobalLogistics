package com.squmish.rcuapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squmish.rcuapp.databinding.ItemDashboardBinding
import com.squmish.rcuapp.model.pendingRequest.GetPendingRequestData
import com.squmish.rcuapp.viewmodel.DashboardViewModel

class DashboardViewHolder (val binding: ItemDashboardBinding, val viewModel: DashboardViewModel) :  RecyclerView.ViewHolder(binding.root){


    fun bind(data: GetPendingRequestData) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
        binding.viewmodel = viewModel
    }
}