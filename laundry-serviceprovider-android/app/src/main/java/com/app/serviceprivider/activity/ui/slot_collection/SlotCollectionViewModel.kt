package com.app.serviceprivider.activity.ui.slot_collection

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.serviceprivider.bean.AddTimeSlotResponce
import com.app.serviceprivider.bean.TimeslotModel
import com.singlevendor.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject

class SlotCollectionViewModel: BaseViewModel() {

    var responceTimeSlotResponce = MutableLiveData<AddTimeSlotResponce>()

    var error = MutableLiveData<Throwable>()






   fun getAddTimeSlot(
        access_token:String,

        slot_type:String,
        day:String,
        time_slots:ArrayList<TimeslotModel>
    ){

       val jsonarray= JSONArray()
       for (item in time_slots) {
           val timeslotModel:TimeslotModel
           timeslotModel=item
           val json1 = JSONObject()
           json1.put("start_time", timeslotModel.start_time)
           json1.put("end_time", timeslotModel.end_time)
           jsonarray.put(json1)
       }


       Log.d("jsontag3",jsonarray.toString())


        apiInterface.getAddTimeSlot(
            access_token = access_token,
            time_slots=jsonarray.toString(),
            slot_type = slot_type,
            day = day

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: AddTimeSlotResponce){

        this.responceTimeSlotResponce.value=response
    }


    fun onFailure(it : Throwable){
        error.value=it

    }
}