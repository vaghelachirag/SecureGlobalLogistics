package com.squmish.rcuapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squmish.rcuapp.databinding.ItemDocumentBinding
import com.squmish.rcuapp.model.getverificationDetailResponse.GetVerificationDocument
import com.squmish.rcuapp.viewmodel.verificationDetail.BasicInformationViewModel

class DocumentViewHolder(val binding: ItemDocumentBinding, val viewModel: BasicInformationViewModel) :  RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GetVerificationDocument) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
        binding.viewmodel = viewModel
    }
}