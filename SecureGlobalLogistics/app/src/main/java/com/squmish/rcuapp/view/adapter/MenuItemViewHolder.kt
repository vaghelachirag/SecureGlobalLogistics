package com.squmish.rcuapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squmish.rcuapp.databinding.LayoutMenuItemBinding
import com.squmish.rcuapp.model.getMenuListResponse.GetMenuListData


class MenuItemViewHolder(val binding: LayoutMenuItemBinding) :  RecyclerView.ViewHolder(binding.root) {
    fun bind(data: GetMenuListData) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }

}