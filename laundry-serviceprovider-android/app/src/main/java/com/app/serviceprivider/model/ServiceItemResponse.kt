package com.app.serviceprivider.model

data class ServiceItemResponse(

    var message: String?,
    var response: List<Response>?
) {
    data class Response(
        var __v: Int?,
        var _id: String?,
        var category: String?,
        var is_service_active: Boolean?,
        var item_id: String?,
        var item_name: String?,
        var price: String?,
        var seller_id: String?,
        var service_type: String?,
        var service_type_name: String?,
       var category_name: String?
    )
}