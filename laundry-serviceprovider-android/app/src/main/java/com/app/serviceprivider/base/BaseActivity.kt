package com.app.serviceprivider.base

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import com.app.serviceprivider.R
import com.app.serviceprivider.constants.AppConstants

import com.app.serviceprivider.utils.showShortToast
import com.app.serviceprivider.utils.ErrorUtil


import com.app.serviceprivider.webservice.ApiInterface
import com.singlevendor.utils.ProgressDialogUtils
import com.singlevendor.webservice.RetrofitUtil


import kotlinx.android.synthetic.main.layout_permission.view.*

abstract class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitFromApp = false
    private val permissionList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
//        checkPermission()
    }

    abstract fun inItId()
    abstract fun setListener()

    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount > 0 -> super.onBackPressed()

            isTaskRoot -> {
                if (doubleBackToExitFromApp) {
                    super.onBackPressed()
                    return
                } else {
                    doubleBackToExitFromApp = true
                    showShortToast(getString(R.string.exit_from_app_message))
                    Handler().postDelayed({
                        doubleBackToExitFromApp = false
                    }, AppConstants.APP_EXIT_MESSAGE_TIME)
                }

            }
            else -> super.onBackPressed()
        }

    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermissions(
                    this, *permissionList
                )
            ) { //            Log.i(TAG, "checkPermission: " + count++);
                ActivityCompat.requestPermissions(
                    this,
                    permissionList,
                    AppConstants.PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == AppConstants.PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == -1) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                ) {

                    showPermissionPopup(AppConstants.LOCATION_PERMISSION_MESSAGE)

                }
            } else if (grantResults[1] == -1) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    showPermissionPopup(AppConstants.STORAGE_PERMISSION_MESSAGE)

                }
            } else if (grantResults[2] == -1) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CAMERA
                    )
                ) {
                    showPermissionPopup(AppConstants.CAMERA_PERMISSION_MESSAGE)

                }
            } else {
//                Toast.makeText(this, " Permissions  granted ", Toast.LENGTH_SHORT).show()
            }

        }
    }


    // showing a permission popup
    private fun showPermissionPopup(type: String) {
        var view: View? = null
        val dialog = Dialog(this)
        view = LayoutInflater.from(this)
            .inflate(R.layout.layout_permission, null, false)

        if (view != null) {
            dialog.setContentView(view)
            dialog.setCanceledOnTouchOutside(false)
            val window = dialog.window
            if (window != null) {
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


                val lp = window.attributes

                view.btn_not_now_permission.setOnClickListener {
                    dialog.dismiss()

                }
                view.btn_continue_permission.setOnClickListener {
//                    dialog.dismiss()
//                    openSettingForPermission()
                }

                lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            }
            dialog.show()
        }
    }

    private fun openSettingForPermission() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
        finish()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)

    }
    fun onError(it: Throwable?) {
        ProgressDialogUtils.getInstance().hideProgress()
        ErrorUtil.handlerGeneralError(applicationContext, it!!)
    }


    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.createBaseApiService()
    }


   // val progressDialog = ProgressDialogUtils().getInstance()
}



