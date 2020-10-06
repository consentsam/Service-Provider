package com.app.serviceprivider.utils

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.serviceprivider.constants.AppConstants

import java.io.File

object ImageUtils {


    fun selectImageDialog(context: Context?) {
        val option = arrayOf("Take Photo", "Choose From Library")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose Action")
        builder.setItems(option) { dialogInterface, i ->
            if (i == 0) {
                openCamera(context)
            } else if (i == 1) {
                openGallery(context)
            } else if (i == 2) {
                openVideo(context)
            }
        }
        builder.show()
    }

    fun selectImageByCamera(context: Context?, mediaType: Int) {

        if (mediaType == 0) {
            openCamera(context)
        } else if (mediaType == 1) {
            openGallery(context)
        }
    }


    fun selectImageVideoDialog(context: Context?) {
        val option = arrayOf("Take Photo", "Choose From Library", "Select Video From Gallery")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose Action")
        builder.setItems(option) { dialogInterface, i ->
            if (i == 0) {
                openCamera(context)
            } else if (i == 1) {
                openGallery(context)
            } else if (i == 2) {
                openVideo(context)
            }
        }
        builder.show()
    }

    private fun openCamera(context: Context?) {
        val cameraintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        (context as AppCompatActivity).startActivityForResult(cameraintent, AppConstants.CAMERA)
    }

    private fun openGallery(context: Context?) {
        val galleryintent = Intent(Intent.ACTION_GET_CONTENT)
        galleryintent.type = "image/*"
        (context as AppCompatActivity).startActivityForResult(galleryintent, AppConstants.GALLERY)
    }

    private fun openVideo(context: Context?) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "video/*"
        (context as AppCompatActivity).startActivityForResult(intent, AppConstants.VIDEO)
    }

    fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        context: Context?,
        mListener: ImageListener,
        from: String
    ) {


        if (requestCode == AppConstants.GALLERY && resultCode == RESULT_OK) {
            val imageuri = data?.data
            val file = compressImageFile(context, imageuri!!)
            mListener.getImageData(imageuri, null, file, AppConstants.MEDIA_TYPE_IMAGE, from)

        } else if (requestCode == AppConstants.CAMERA && resultCode == RESULT_OK) {
            val bitmap: Bitmap = data!!.extras!!.get("data") as Bitmap
            val imageuri = (context as AppCompatActivity).getImageUri(bitmap)
            val file = compressImageFile(context, imageuri)
            mListener.getImageData(imageuri, bitmap, file, AppConstants.MEDIA_TYPE_IMAGE, from)
        } else if (requestCode == AppConstants.VIDEO && resultCode == RESULT_OK) {
            val videoUri = data?.data
//            val file = File(FileUtils.getUriRealPath(context!!, videoUri!!))
            val file = File(
                RealPathUtil.getRealPath(
                    context!!,
                    videoUri!!
                )!!
            )//FileUtils.getUriRealPath(context!!, videoUri!!))
            var length = file.length()
            if (file.path.isNotEmpty()) {
                length = length.div(1024).div(1024)
                Log.e("MyVideo", length.toString() + " MB")
                if (length.toInt() > 15) {
                    (context as AppCompatActivity).showShortToast(
                        "Error video limit"
                    )
                } else {
                    val bm = getVideoThumbnails(context, videoUri)[0]
                    mListener.getImageData(
                        (context as AppCompatActivity).getImageUri(bm),
                        bm,
                        file,
                        AppConstants.MEDIA_TYPE_VIDEO, from
                    )
                }
            } else {
                (context as AppCompatActivity).showShortToast("Video Error")
            }
        }
    }
}
