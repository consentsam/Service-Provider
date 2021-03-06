package com.app.serviceprivider.ui.activity.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.app.serviceprivider.R
import com.app.serviceprivider.activity.ui.home.MainActivity
import com.app.serviceprivider.base.BaseActivity
import com.app.serviceprivider.bean.LoginResponse
import com.app.serviceprivider.ui.activity.forgotpassword.ForgotPasswordActivity
import com.app.serviceprivider.ui.activity.otpverify.OtpVerifyActivity
import com.app.serviceprivider.ui.activity.register.RegisterActivity
import com.app.serviceprivider.utils.*
import com.google.firebase.iid.FirebaseInstanceId

import com.warranty.handle.utils.IntentConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.button_layout.view.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    private var disposable: Disposable? = null

    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inItId()
        setListener()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun inItId() {
        // for dont have account
        val dontHaveAccount = tv_not_account.text.toString()
        getSpannableString(dontHaveAccount, tv_not_account, 23, dontHaveAccount.length, this)

        inc_btn_login.btn_submit.text = getString(R.string.login)

    }

    override fun setListener() {
        inc_btn_login.btn_submit.setOnClickListener(this)
        tv_not_account.setOnClickListener(this)
        iv_show_hide_password_login.setOnClickListener(this)
        tv_forgot_password_login.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_submit -> {
                // for empty check only
//                sendIntent()

                if (NetworkUtils.isInternetAvailable(this)) {
                    if (isValidInputs()) {
                        login()
                    }

                } else {
                    showToast(resources.getString(R.string.error_internet), applicationContext)
                }
            }
            R.id.tv_not_account -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.iv_show_hide_password_login -> {
                showHidePassword(et_password_login, iv_show_hide_password_login)
            }
            R.id.tv_forgot_password_login -> {
                val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun isValidInputs(): Boolean {

        if (et_mobile_number_login.text.isEmpty() && et_password_login.text.isEmpty()) {
            et_mobile_number_login.error = resources.getString(R.string.enter_mobile_number)
            et_password_login.error = resources.getString(R.string.error_password)
            et_mobile_number_login.requestFocus()
            return false
        }
        if (et_mobile_number_login.getString().isEmpty()) {
            et_mobile_number_login.error = resources.getString(R.string.enter_mobile_number)
            et_mobile_number_login.requestFocus()
            return false
        }
        if (!et_mobile_number_login.getString().isValidMobile) {
            et_mobile_number_login.error = resources.getString(R.string.error_valid_mobile)
            et_mobile_number_login.requestFocus(et_mobile_number_login.length())
            return false
        }
        if (et_password_login.getString().isEmpty()) {
            et_password_login.error = resources.getString(R.string.error_password)
            et_password_login.requestFocus()
            return false
        }
        if (!et_password_login.getString().isValidPassword) {
            et_password_login.error = resources.getString(R.string.error_invalid_password)
            et_password_login.requestFocus(et_password_login.length())
            return false
        }
        return true
    }


    private fun login() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if (it.isSuccessful) {
                val deviceToken = it.result?.token
                Log.e("deviceTokenn>>>>", deviceToken)
                disposable = apiInterface.login(
                    mobileNumber = et_mobile_number_login.getString(),
                    deviceType = "2",
                    deviceToken = deviceToken!!,
                    latitude = "72.00",
                    longitude = "76.00",
                    password = et_password_login.getString(),
                    countryCode = "977"
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {

                            onLoginSuccess(it)
                        },
                        {

                            onError(it)
                            showToast("Invalid credentials", applicationContext)
                        }
                    )
            }
        }


    }

    private fun onLoginSuccess(response: LoginResponse) {

        showToast(response.message, applicationContext)
        if (response.message.equals("Please verify otp")) {

            startActivity(Intent(this, OtpVerifyActivity::class.java).apply {
                putExtra("otp", response.response!!.verification_code)
                // 0 = user & 1= provider
                putExtra("access_token", response.response!!.access_token)
                putExtra("country_code", response.response!!.countryCode)
                putExtra("mobile_number", response.response!!.mobileNumber)
                putExtra("country_code", response.response!!.countryCode)
                putExtra(IntentConstants.FROM, IntentConstants.FROM_SIGN_UP)
                startActivity(intent)
                finish()
            }
            )


        } else {

            proceedFurther(response)
        }
    }

    private fun proceedFurther(response: LoginResponse) {

        SharePrefUtils.getSharedInstance(this@LoginActivity).is_login_successful = true
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))

        SharePrefUtils.getSharedInstance(this).mobileNumber = response.response.mobileNumber
        SharePrefUtils.getSharedInstance(this).accessToken = response.response.access_token
        /* val intent = Intent(this@LoginActivity, MainActivity::class  .java)
         startActivity(intent)*/
        finish()
    }
}