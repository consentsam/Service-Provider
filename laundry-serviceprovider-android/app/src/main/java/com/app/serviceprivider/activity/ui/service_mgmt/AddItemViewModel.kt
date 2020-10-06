package com.app.serviceprivider.activity.ui.service_mgmt

import androidx.lifecycle.MutableLiveData
import com.app.serviceprivider.model.*
import com.singlevendor.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddItemViewModel: BaseViewModel() {

    var responseCategory = MutableLiveData<GetCategoryResponse>()
    var responseServiceType = MutableLiveData<GetServiceTypeResponse>()
    var responseItemName = MutableLiveData<GetItemNameResponse>()
    var responseAddService = MutableLiveData<AddServiceResponse>()
    var responseServiceItem = MutableLiveData<ServiceItemResponse>()
    var error = MutableLiveData<Throwable>()

    fun getCategoryItem(

    ){

        apiInterface.getCategoryItem(


        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: GetCategoryResponse){

        this.responseCategory.value=response
    }

    fun getAddServiceType(

    ){

        apiInterface.getAddServiceType(

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: GetServiceTypeResponse){

        this.responseServiceType.value=response
    }

    fun getItemName(

        category: String

    ){

        apiInterface.getItemName(
            catId = category

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: GetItemNameResponse){

        this.responseItemName.value=response
    }

    fun addService(
        access_token:String,
        service_type:String,
        category:String,
        item_id:String,
        price:String
    ){

        apiInterface.addService(
            access_token = access_token,
            service_type = service_type,
            category = category,
            item_id = item_id,
            price = price
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: AddServiceResponse){

        this.responseAddService.value=response
    }

    fun getServiceItem(
        access_token:String,
        service_type:String
    ){

        apiInterface.getServiceItem(
            access_token = access_token,
            service_type = service_type

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onSuccess(it) },
            { onFailure(it) })
    }


    fun onSuccess(response: ServiceItemResponse){

        this.responseServiceItem.value=response
    }

    fun onFailure(it : Throwable){
        error.value=it

    }
}