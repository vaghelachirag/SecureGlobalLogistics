package com.app.secureglobal.view.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.secureglobal.R
import com.app.secureglobal.databinding.ItemDashboardSelectionBinding
import com.app.secureglobal.model.dashboard.getDashboardApiResponse.GetMobileDashboardDetailDto
import com.app.secureglobal.viewmodel.DashboardMenuViewModel

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
            .into(binding.image);}}
  