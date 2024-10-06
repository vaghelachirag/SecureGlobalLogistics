package com.app.secureglobal.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.secureglobal.databinding.ItemDocumentBinding
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDocument
import com.app.secureglobal.viewmodel.verificationDetail.BasicInformationViewModel

class DocumentViewHolder(val binding: ItemDocumentBinding, val viewModel: BasicInformationViewModel) :  RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GetVerificationDocument) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
        binding.viewmodel = viewModel
    }
}