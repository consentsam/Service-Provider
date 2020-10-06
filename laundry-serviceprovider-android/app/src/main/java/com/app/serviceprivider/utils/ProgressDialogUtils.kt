package com.singlevendor.utils

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.app.serviceprivider.R

class ProgressDialogUtils {

    private var mDialog: Dialog? = null
    private var dialog: ProgressDialog ?=null

    companion object {
        var progressDialog: ProgressDialogUtils? = null
        fun getInstance(): ProgressDialogUtils {
            if (progressDialog == null) {
                progressDialog = ProgressDialogUtils()
            }
            return progressDialog as ProgressDialogUtils
        }
    }

    fun showProgress(context: Context,  cancelable: Boolean) {
//        try {
//            if (mDialog == null) {
        mDialog = Dialog(context)
        mDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog?.setContentView(R.layout.custom_progress_dailog)
        mDialog?.setCancelable(cancelable)
        mDialog?.setCanceledOnTouchOutside(cancelable)
        mDialog?.show()
//            } else {
//                mDialog?.setCancelable(cancelable)
//                mDialog!!.show()
//            }
//        } catch (e: Exception){
//            Log.e("MyExceptions", e.message)
//        }
    }

    fun hideProgress() {
        if (mDialog != null) {
            mDialog?.dismiss()
            mDialog = null
        }
    }

    fun dispose() {
        if (progressDialog != null) {
            progressDialog == null
        }
    }
}