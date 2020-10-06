package com.singlevendor.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.serviceprivider.webservice.ApiInterface
import com.singlevendor.webservice.RetrofitUtil

abstract class BaseViewModel: ViewModel() {


    var mError = MutableLiveData<Throwable>()

    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.createBaseApiService()
    }



}