package com.app.serviceprivider.activity.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.app.serviceprivider.R
import com.app.serviceprivider.base.BaseActivity
import com.app.serviceprivider.bean.ProfileResponse
import com.app.serviceprivider.utils.getString
import com.app.serviceprivider.utils.isValueEmpty
import com.app.serviceprivider.utils.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_profile_createnext.*

class ProfileBankDetailActivity : BaseActivity(), View.OnClickListener{
    private var disposable: Disposable? = null
    private var access_token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_createnext)
        access_token = intent.extras?.getString("access_token")
        Log.e(" " + access_token, " " + access_token)
        inItId()
        setListener()
    }

    override fun inItId() {

    }

    override fun setListener() {
       continue_btn.setOnClickListener(this)
        skip_bank_detail_tv.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.continue_btn -> {
                // for empty check only
//                sendIntent()

                if (NetworkUtils.isInternetAvailable(this)) {
                    if (
                        et_bank_name.isValueEmpty("Bank name") &&
                        et_bank_acc_no.isValueEmpty("Account Number") &&
                        et_acc_holder_name.isValueEmpty("Account Holder Name") &&
                        et_iban.isValueEmpty("IBAN Code")

                    ) {
                        bankDetail()
                    }

                } else {
                    showToast(resources.getString(R.string.error_internet),applicationContext)
                }


            }
            R.id.skip_bank_detail_tv->
            {
                sendIntent()
            }

        }

    }


    private fun bankDetail() {


        disposable = apiInterface.bank_detail(
            access_token = access_token!!,
            bank_name = et_bank_name.getString(),
            bank_account_number = et_bank_acc_no.getString(),
            account_holder_name =et_acc_holder_name.getString(),
            iban_code = et_iban.getString()

        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    onLoginSuccess(it)
                },
                {

                    onError(it)
                    showToast("Something went wrong",applicationContext)
                }
            )

    }

    private fun onLoginSuccess(response: ProfileResponse) {

        showToast(response.message,applicationContext)


        proceedFurther(response)

    }
    private fun proceedFurther(response: ProfileResponse) {
        val intent = Intent(this, ProfileUploadDocumentActivity::class  .java)

            intent.putExtra("access_token",access_token)

        startActivity(intent)
        finish()
    }

    private fun sendIntent() {
        val intent = Intent(this, ProfileUploadDocumentActivity::class.java)

            intent.putExtra("access_token",access_token)

        startActivity(intent)
        finish()
    }
}