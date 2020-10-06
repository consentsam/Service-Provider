package com.app.serviceprivider.ui.activity.profile

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.view.View
import com.app.serviceprivider.R
import com.app.serviceprivider.activity.profile.ProfileBankDetailActivity
import com.app.serviceprivider.base.BaseActivity
import com.app.serviceprivider.bean.ProfileResponse
import com.app.serviceprivider.utils.getString

import com.app.serviceprivider.utils.isValueEmpty
import com.app.serviceprivider.utils.showToast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_profile_create.*


import java.io.File

class ProfileCreateActivity : BaseActivity(), View.OnClickListener {
    private var disposable: Disposable? = null
    private var access_token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_create)
        access_token = intent.extras?.getString("access_token")

        Log.e(" " + access_token, " " + access_token)
        inItId()
        setListener()
    }

    override fun inItId() {


    }

    override fun setListener() {
        img_profile.setOnClickListener(this)
        upload_document_btn.setOnClickListener(this)


    }
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.upload_document_btn -> {
                // for empty check only
//                sendIntent()
                if (NetworkUtils.isInternetAvailable(this)) {
                    if (
                        et_laundry_name.isValueEmpty("name") &&
                        et_owner_name.isValueEmpty("name") &&
                        et_mobile_number_profile.isValueEmpty("mobile number") &&
                        et_email_profile.isValueEmpty("email id") &&
                        et_laundry_address.isValueEmpty("laundry address") &&
                        et_laundry_contact_number.isValueEmpty("laundry mobile number")

                    ) {
                        creteProfile()
                    }

                } else {
                    showToast(resources.getString(R.string.error_internet),applicationContext)
                }

            }
            R.id.img_profile->
            {
                val intent = CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .getIntent(this)
                startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
            }

        }
    }

    private fun creteProfile() {


        disposable = apiInterface.profile_creation(
            access_token = access_token!!,
            laudary_name = et_laundry_name.getString(),
            owner_name = et_owner_name.getString(),
            mobileNumber =et_mobile_number_profile.getString(),
            email = et_email_profile.getString(),
            countryCode = "977",
            address =et_laundry_address.getString(),
            delivery_mobileNumer =et_laundry_contact_number.getString()
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
        val intent = Intent(this, ProfileBankDetailActivity::class  .java)

            intent.putExtra("access_token",response.response!!.access_token)

        startActivity(intent)
        finish()
    }

    private fun sendIntent() {
        val intent = Intent(this, ProfileBankDetailActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && data != null) {
            var result: CropImage.ActivityResult = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                img_profile.setImageURI(result.uri)
                // handling and storing image in shared pref
                result.uri.path?.also {
                    val imgFile = File(it)
//                    fileImg = imgFile
                }
                if (result.uri != null) {
//                    sharedPreferenceUtil.profileImage = result.uri.toString()
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error: Exception = result.error
            } else {

            }
        }
    }
}