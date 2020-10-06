package com.singlevendor.base

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.serviceprivider.webservice.ApiInterface
import com.singlevendor.webservice.RetrofitUtil

abstract class NewBaseActivity : AppCompatActivity() {

    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.createBaseApiService()
    }
   abstract fun init()

    abstract fun initControl()

    override fun onStart() {
        super.onStart()
        Log.e("BaseCallback", "OnStartCalled")
    }

    override fun onStop() {
        super.onStop()
        Log.e("BaseCallback", "OnStopCalled")
    }




}


