package com.app.serviceprivider.dialogs

import android.content.Context


object DialogBox {

    fun showVerificationPopup(context: Context, message: String, listener: DialogInteractionListener) {

  /*      var view: View? = null
        val dialog = Dialog(context)
        if (context is BaseActivity) {
            view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_otp_verification, null, false)

            if (view != null) {
                dialog.setContentView(view)
                dialog.setCanceledOnTouchOutside(false)
                // set title
                view.tv_message.text = message
                val window = dialog.window
                if (window != null) {
                    window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                    val display = wm.defaultDisplay
                    val metrics = DisplayMetrics()
                    display.getMetrics(metrics)
                    val lp = window.attributes
                    lp.width = metrics.widthPixels - 120
                    lp.height = (metrics.heightPixels/4)

                    view.cv_otp_verify.setOnClickListener {
                        dialog.dismiss()
                        listener.onSelectClick()
                    }
                    lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                }
                dialog.show()
            }
        }*/
    }
}
interface DialogInteractionListener
{
    fun onCancelClick()
    fun onSelectClick()
}