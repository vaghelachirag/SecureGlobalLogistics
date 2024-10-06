package com.app.secureglobal.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.app.secureglobal.R
import com.app.secureglobal.databinding.LayoutMenuItemBinding
import com.app.secureglobal.interfaces.OnItemSelected
import com.app.secureglobal.model.getMenuListResponse.GetMenuListData


class MenuItemAdapter(val context: Context, private val list: ArrayList<GetMenuListData>, val onItemSelected: OnItemSelected<GetMenuListData>) :  RecyclerView.Adapter<MenuItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<LayoutMenuItemBinding>(
            layoutInflater,
            R.layout.layout_menu_item,
            parent,
            false
        )
        return MenuItemViewHolder(binder)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MenuItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context).load(list[position].getIcon()).apply(options).into(holder.binding.ivLogo)
        holder.binding.txtMenuName.text = list[position].getName()

        holder.binding.llMain.setOnClickListener {
            onItemSelected.onItemSelected(list[position], position)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
}