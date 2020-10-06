package com.app.serviceprivider.listner

interface MyEventListner {

    fun OnDeleteListner(pos:Int,service_id:String)
    fun OnEditItemListner(pos:Int,service_type:String,category:String,item_id:String,id:String)
    fun OnChangeStatus(pos:Int,service_id:String,change_status:String,service_type:String,category:String,item_id:String)
}