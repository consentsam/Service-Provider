package com.singlevendor.base

import androidx.fragment.app.Fragment
import com.app.serviceprivider.webservice.ApiInterface
import com.singlevendor.webservice.RetrofitUtil


abstract open class BaseFragment: Fragment() {

    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.createBaseApiService()
    }

    abstract fun init()
    abstract fun initControl()
}