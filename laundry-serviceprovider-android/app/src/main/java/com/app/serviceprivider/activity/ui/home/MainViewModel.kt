package com.app.serviceprivider.activity.ui.home

import androidx.lifecycle.MutableLiveData
import com.app.serviceprivider.model.OnlineDashboardModel
import com.singlevendor.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel: BaseViewModel() {


    var resOnlineDashboard = MutableLiveData<OnlineDashboardModel>()
    var error = MutableLiveData<Throwable>()

    fun getOnlineDash(

        access_token: String,
        bank:String

    ){

        apiInterface.getOnlineDash(
            access_token = access_token,
            bank_name =  bank


        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }



    fun onSuccess(response: OnlineDashboardModel){
        this.resOnlineDashboard.value=response
    }


    fun onFailure(it : Throwable){
        try {
            error.value=it
        }catch (e: Exception){

        }


    }
}