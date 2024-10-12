package com.app.secureglobal.view.dialougs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.app.secureglobal.R
import com.app.secureglobal.databinding.DialogWithOkButtonBinding

class MessageDialog(context: Context, val message: String?) : Dialog(context, R.style.ThemeDialog) {
    private lateinit var binding: DialogWithOkButtonBinding
    private var listener: OkButtonListener? = null
    private var cancelListener: OkButtonListener? = null
    private var isCancelButton: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_with_ok_button,
            null,
            false
        )

        setContentView(binding.root)

        if (!message.isNullOrEmpty()) binding.tvMessage.text = message
        binding.btnCancel.visibility = if (isCancelButton!!) View.GONE else View.GONE
        binding.btnOk.setOnClickListener {
            if (listener != null) listener?.onOkPressed(this)
            else dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    fun setListener(listener: OkButtonListener?): MessageDialog {
        this.listener = listener
        return this
    }

    fun setCancelButton(isCancelButton: Boolean?): MessageDialog {
        this.isCancelButton = isCancelButton
        return this
    }

    interface OkButtonListener {
        fun onOkPressed(dialog: MessageDialog)
    }

}