package com.squmish.rcuapp.view.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.rcuapp.view.dialougs.ProgressDialog
import com.squmish.rcuapp.R
import com.squmish.rcuapp.uttils.Session


open class BaseFragment : Fragment() {
    lateinit var mContext: Context
    lateinit var mActivity: Activity
    private var progressDialog: ProgressDialog? = null
    lateinit var session: Session


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
        mActivity = context as Activity
        session = Session(mActivity)

    }

    fun showProgressbar(message: String? = requireActivity().getString(R.string.please_wait)) {
        hideProgressbar()
        if (progressDialog == null) {
            progressDialog = ProgressDialog(requireContext(), message)
        }
        progressDialog?.show()
    }


     fun hideProgressbar() {
        if (isAdded&&progressDialog != null && progressDialog?.isShowing!!) progressDialog!!.dismiss()
    }

}
