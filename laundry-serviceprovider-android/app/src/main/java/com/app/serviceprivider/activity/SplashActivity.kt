package com.app.serviceprivider.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.app.serviceprivider.R
import com.app.serviceprivider.activity.ui.home.MainActivity
import com.app.serviceprivider.constants.AppConstants
import com.app.serviceprivider.ui.activity.login.LoginActivity
import com.app.serviceprivider.utils.SharePrefUtils

import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var mHandler: Handler = Handler()
    private var runnable: Runnable = Thread()
    private var isAnimationComplete: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fullScreen()
        loadGif()
        setSplashScreen()

    }

    private fun fullScreen() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        if (isAnimationComplete == 1) {
            Glide.with(this).load(R.drawable.splash).into(img_animation)
            sendIntent()
        }
    }

    private fun loadGif() {
        Glide.with(this).asGif()
            .load(R.raw.splashanim)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {

                    resource?.setLoopCount(1)
                    isAnimationComplete = 1

                    return false
                }

            }).into(img_animation)


    }

    private fun setSplashScreen() {

        runnable = Runnable {

            sendIntent()
        }

        mHandler.postDelayed(
            runnable
            , AppConstants.SPLASH_TIME
        ) // wait for 3.5 seconds
    }

    private fun sendIntent() {

        if (SharePrefUtils.getSharedInstance(this).is_login_successful) {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )

        } else
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
        finish()

       /* val intent = Intent(this, SelectLanguageActivity::class.java)
        startActivity(intent)
        finishAffinity()*/
    }


    override fun onStop() {
        super.onStop()
        mHandler.removeCallbacks(runnable)
    }
}