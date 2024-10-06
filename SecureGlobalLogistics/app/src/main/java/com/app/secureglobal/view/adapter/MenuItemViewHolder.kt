package com.app.secureglobal.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.secureglobal.databinding.LayoutMenuItemBinding
import com.app.secureglobal.model.getMenuListResponse.GetMenuListData


class MenuItemViewHolder(val binding: LayoutMenuItemBinding) :  RecyclerView.ViewHolder(binding.root) {
    fun bind(data: GetMenuListData) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }

}