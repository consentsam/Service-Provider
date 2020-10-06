package com.app.serviceprivider.activity.profile

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import com.app.serviceprivider.Constants
import com.app.serviceprivider.R
import com.app.serviceprivider.base.BaseActivity
import com.app.serviceprivider.ui.activity.login.LoginActivity
import com.app.serviceprivider.utils.showToast
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_profile_create2.*


class ProfileUploadDocumentActivity : BaseActivity(), View.OnClickListener {
    private var disposable: Disposable? = null
    private var access_token: String? = null
    var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_create2)
        access_token = intent.extras?.getString("access_token")
        inItId()
        setListener()
    }

    override fun inItId() {

    }

    override fun setListener() {
        proceed_btn.setOnClickListener(this)
        skip_bank_detail2_tv.setOnClickListener(this)
        iv_upload_commercial_profile_iv.setOnClickListener(this)
        iv_municipality_license.setOnClickListener(this)
        iv_tax_reg_number.setOnClickListener(this)
        iv_other_doc.setOnClickListener(this)
     iv_address_prove.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.proceed_btn -> {
                // for empty check only
//                sendIntent()
                if (NetworkUtils.isInternetAvailable(this)) {

                        //uploadProfile()
                    openDialog()

                } else {
                    showToast(resources.getString(R.string.error_internet),applicationContext)
                }



            }
            R.id.skip_bank_detail2_tv -> {
                openDialog()
            }
            R.id.iv_upload_commercial_profile_iv -> {
                var chooseFile = Intent(Intent.ACTION_GET_CONTENT)
                chooseFile.type = "*/*"
                chooseFile = Intent.createChooser(chooseFile, "Choose a file")
                startActivityForResult(chooseFile, Constants.PICKFILE_RESULT_CODE1)
            }
            R.id.iv_municipality_license -> {
                var chooseFile1 = Intent(Intent.ACTION_GET_CONTENT)
                chooseFile1.type = "*/*"
                chooseFile1 = Intent.createChooser(chooseFile1, "Choose a file")
                startActivityForResult(chooseFile1, Constants.PICKFILE_RESULT_CODE2)
            }
            R.id.iv_tax_reg_number -> {
                var chooseFile = Intent(Intent.ACTION_GET_CONTENT)
                chooseFile.type = "*/*"
                chooseFile = Intent.createChooser(chooseFile, "Choose a file")
                startActivityForResult(chooseFile, Constants.PICKFILE_RESULT_CODE3)
            }
            R.id.iv_other_doc -> {
                var chooseFile = Intent(Intent.ACTION_GET_CONTENT)
                chooseFile.type = "*/*"
                chooseFile = Intent.createChooser(chooseFile, "Choose a file")
                startActivityForResult(chooseFile, Constants.PICKFILE_RESULT_CODE4)
            }
            R.id.iv_address_prove -> {
                var chooseFile = Intent(Intent.ACTION_GET_CONTENT)
                chooseFile.type = "*/*"
                chooseFile = Intent.createChooser(chooseFile, "Choose a file")
                startActivityForResult(chooseFile, Constants.PICKFILE_RESULT_CODE5)
            }
        }
    }


  /*  private fun uploadProfile() {


        disposable = apiInterface.upload_document(
            access_token = access_token!!,
            commercial_registation = et_commercial_registration.text.Multi,
            municipality_license = et_owner_name.getString(),
            tax_registation_number =et_mobile_number_profile.getString(),
            other_document = et_email_profile.getString(),
            address_proof = "977",

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
        val intent = Intent(this, ProfileCreatenextActivity::class  .java)

        intent.putExtra("access_token",response.response!!.access_token)

        startActivity(intent)
        finish()
    }*/
    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.register_dialog)



        val button: Button = dialog.findViewById(R.id.tv_okay) as Button
        button.setOnClickListener(View.OnClickListener { dialog.dismiss()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        })

        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
           Constants.PICKFILE_RESULT_CODE1 -> if (resultCode === -1) {
               uri = data?.getData()

               et_commercial_registration.setText(uri?.path)
            }
            Constants.PICKFILE_RESULT_CODE2 -> if (resultCode === -1) {
                uri = data?.getData()

                et_municipality_license.setText(uri?.path)
            }
            Constants.PICKFILE_RESULT_CODE3 -> if (resultCode === -1) {
                uri = data?.getData()

                et_tax_reg_number.setText(uri?.path)
            }
            Constants.PICKFILE_RESULT_CODE4-> if (resultCode === -1) {
                uri = data?.getData()

                et_other_doc.setText(uri?.path)
            }
            Constants.PICKFILE_RESULT_CODE5 -> if (resultCode === -1) {
                uri = data?.getData()

                et_address_prove.setText(uri?.path)
            }

        }
    }
}