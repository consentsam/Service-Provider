package com.app.serviceprivider.activity.ui.task

import androidx.lifecycle.MutableLiveData
import com.app.serviceprivider.model.*
import com.singlevendor.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TaskViewModel: BaseViewModel() {

    var getOrderResponse = MutableLiveData<GetOrderResponse>()

    var error = MutableLiveData<Throwable>()




    fun getOrder(
        access_token:String,
        type:String

    ){

        apiInterface.getOrder(
            access_token = access_token,
            type = type

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }



    fun getOrderChangeStatus(
        access_token:String,
        status:String,
        order_id:String
    ){

        apiInterface.getOrderChangeStatus(
            access_token = access_token,
            status=status,
            order_id = order_id

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }

    fun onSuccess(response: GetOrderResponse){

        this.getOrderResponse.value=response
    }


    fun onFailure(it : Throwable){
        error.value=it

    }
}