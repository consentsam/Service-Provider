package com.app.serviceprivider.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.app.serviceprivider.R
import com.app.serviceprivider.base.BaseActivity
import com.app.serviceprivider.utils.ErrorResponse
import com.app.serviceprivider.utils.getGsonInstance
import com.app.serviceprivider.utils.showShortToast

import kotlinx.android.synthetic.main.dialog_error.*

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

object ErrorDialog {

    fun errorDialog(context: Context, title: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_error)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.tv_title.text = title
        dialog.tv_close_popup.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }


    fun somethingWentWrong(context: Context) {
        errorDialog(context, "Something went wrong.")

    }

    fun displayError(context: Context, socketTimeoutException: SocketTimeoutException) {
//        DialogUtil().errorDialog(context,socketTimeoutException.message!! )
        errorDialog(context, context.getString(R.string.check_internet_availability))

    }

    fun displayError(context: Context, connectionException: ConnectException) {
        (context as BaseActivity).showShortToast(context.getString(R.string.check_internet_availability))
//        DialogUtil().errorDialog(context, connectionException.message!!)


    }


    fun displayError(context: Context, exception: HttpException) {
        try {

            val errorBody = getGsonInstance().fromJson(
                exception.response()?.errorBody()?.string(),
                ErrorResponse::class.java
            )
            errorDialog(context, errorBody.message)
//            errorDialog(context, errorBody.message)

        } catch (e: Exception) {
            somethingWentWrong(context)
        }
    }
}