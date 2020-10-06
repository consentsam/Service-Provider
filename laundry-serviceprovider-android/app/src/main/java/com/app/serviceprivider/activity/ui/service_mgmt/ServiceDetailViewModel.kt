package com.app.serviceprivider.activity.ui.service_mgmt

import androidx.lifecycle.MutableLiveData
import com.app.serviceprivider.model.DeleteServiceResponse
import com.app.serviceprivider.model.ServiceItemResponse
import com.app.serviceprivider.model.ServiceStatusResponse
import com.singlevendor.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServiceDetailViewModel: BaseViewModel() {

    var responseServiceItem = MutableLiveData<ServiceItemResponse>()
    var responseDeleteItem = MutableLiveData<DeleteServiceResponse>()
    var responseChnageStatus = MutableLiveData<ServiceStatusResponse>()
    var error = MutableLiveData<Throwable>()

    fun getServiceItem(
        access_token:String,
        sevice_type:String
    ){

        apiInterface.getServiceItem(
            access_token = access_token,
            service_type = sevice_type

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: ServiceItemResponse){

        this.responseServiceItem.value=response
    }

    fun getDeleteItem(
        serviceId:String
    ){

        apiInterface.getDeleteItem(
            serviceId = serviceId

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: DeleteServiceResponse){

        this.responseDeleteItem.value=response
    }

    fun getServiceStatus(
        serviceId:String,
        is_service_active:String
    ){

        apiInterface.getServiceStatus(
            serviceId = serviceId,
            is_service_active = is_service_active


        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: ServiceStatusResponse){

        this.responseChnageStatus.value=response
    }
    fun onFailure(it : Throwable){
        error.value=it

    }

}