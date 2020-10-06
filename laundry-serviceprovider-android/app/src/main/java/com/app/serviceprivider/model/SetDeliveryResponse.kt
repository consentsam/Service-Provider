package com.app.serviceprivider.model

data class SetDeliveryResponse(
    var message: String?,
    var response: Response?

) {
    data class Response(
        var __v: Int?,
        var _id: String?,
        var days_for_express: String?,
        var days_for_normal: String?,
        var eight_to_twelve: String?,
        var express_delivery_charge: String?,
        var four_to_eight: String?,
        var maximum_order_charges: String?,
        var minimum_order_charges: String?,
        var normal_delivery_charge: String?,
        var seller_id: String?,
        var type: String?,
        var zero_to_four: String?
    )


}