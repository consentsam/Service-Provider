package com.app.serviceprivider.activity.ui.coll_delivery_mgmt

import androidx.lifecycle.MutableLiveData
import com.app.serviceprivider.bean.TimeSlotResponce
import com.singlevendor.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CollDelMgmtViewModel: BaseViewModel() {

    var responceTimeSlotResponce = MutableLiveData<TimeSlotResponce>()

    var error = MutableLiveData<Throwable>()





    fun getTimeslot(
        access_token:String,
        slot_type:String,
        day:String
    ){

        apiInterface.getGetTimeSlot(
            access_token = access_token,
            slot_type = slot_type,
            day = day

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: TimeSlotResponce){

        this.responceTimeSlotResponce.value=response
    }


    fun onFailure(it : Throwable){
        error.value=it

    }
}