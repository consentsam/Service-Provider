package com.app.serviceprivider.activity.ui.service_mgmt

import androidx.lifecycle.MutableLiveData
import com.app.serviceprivider.model.GetDeliveryResponse

import com.app.serviceprivider.model.SetDeliveryResponse
import com.singlevendor.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServiceManagentViewModel: BaseViewModel() {

   var responsegetDelivery = MutableLiveData<GetDeliveryResponse>()
    var responseSetDelivery = MutableLiveData<SetDeliveryResponse>()
    var error = MutableLiveData<Throwable>()

  fun getServiceType(
      access_token:String
    ){

        apiInterface.getDeliveryCharge(
            access_token = access_token
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }

    fun onSuccess(response: GetDeliveryResponse){
        this.responsegetDelivery.value=response
    }

    fun setDeliveryCharge(
        access_token:String,
        type:String,
        normal_delivery_charge:String,
        express_delivery_charge:String,
        days_for_normal:String,
        days_for_express:String,
        zero_to_four:String,
        four_to_eight:String,
        eight_to_twelve:String,
        minimum_order_charges:String,
        maximum_order_charges:String

    ){

        apiInterface.setDeliveryCharge(
            access_token = access_token,
            type = type,
            normal_delivery_charge = normal_delivery_charge,
            express_delivery_charge = express_delivery_charge,
            days_for_normal = days_for_normal,
            days_for_express = days_for_express,
            zero_to_four = zero_to_four,
            four_to_eight = four_to_eight,
            eight_to_twelve = eight_to_twelve,
            minimum_order_charges = minimum_order_charges,
            maximum_order_charges = maximum_order_charges

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }

    fun onSuccess(response: SetDeliveryResponse){
        this.responseSetDelivery.value=response
    }
    fun onFailure(it : Throwable){
        error.value=it

    }

}