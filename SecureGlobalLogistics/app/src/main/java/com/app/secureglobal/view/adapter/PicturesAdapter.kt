package com.app.secureglobal.view.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.secureglobal.view.dialougs.FullScreenImageDialoug
import com.app.secureglobal.R
import com.app.secureglobal.databinding.ItemPicturesBinding
import com.app.secureglobal.model.getverificationDetailResponse.GetFiVerificationDocument
import com.app.secureglobal.uttils.AppConstants
import com.app.secureglobal.viewmodel.verificationDetail.PictureViewModel


class PicturesAdapter(val context: Context, private val list: MutableList<GetFiVerificationDocument>, val viewModel: PictureViewModel) :
    RecyclerView.Adapter<PicturesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<ItemPicturesBinding>(
            layoutInflater,
            R.layout.item_pictures,
            parent,
            false
        )

        return PicturesViewHolder(binder,parent.context, viewModel)
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.ivPicture.setOnClickListener {
            Log.e("ImagePath",AppConstants.baseURLImage + "/"+ list[position].getDocumentPath().toString())
            FullScreenImageDialoug(context as Activity, AppConstants.baseURLImage + "/"+ list[position].getDocumentPath()).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}