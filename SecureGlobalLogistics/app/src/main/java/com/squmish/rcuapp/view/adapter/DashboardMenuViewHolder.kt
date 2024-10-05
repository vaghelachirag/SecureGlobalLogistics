package com.squmish.rcuapp.view.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squmish.rcuapp.R
import com.squmish.rcuapp.databinding.ItemDashboardBinding
import com.squmish.rcuapp.databinding.ItemDashboardSelectionBinding
import com.squmish.rcuapp.model.dashboard.getDashboardApiResponse.GetMobileDashboardDetailDto
import com.squmish.rcuapp.model.pendingRequest.GetPendingRequestData
import com.squmish.rcuapp.uttils.AppConstants
import com.squmish.rcuapp.viewmodel.DashboardMenuViewModel
import com.squmish.rcuapp.viewmodel.DashboardViewModel

class DashboardMenuViewHolder (val context: Context, val binding: ItemDashboardSelectionBinding, val viewModel: DashboardMenuViewModel) :  RecyclerView.ViewHolder(binding.root) {


    fun bind(data: GetMobileDashboardDetailDto) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
        binding.viewmodel = viewModel


        Glide.with(context)
            .load(data.getIcon())
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.image);
    }
}