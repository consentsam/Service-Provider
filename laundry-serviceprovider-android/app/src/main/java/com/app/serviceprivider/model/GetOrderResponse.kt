package com.app.serviceprivider.model

data class GetOrderResponse(

    var message: String?,
    var response: List<GetOrderResponse.Response>
) {
    data class Response(

        var pickup_time_slots: Pickup_Time_Slots?,
        var _delivery_time_slots: Delivery_Time_Slots?,
        var status: String?,
        var _id: String?,
        var seller_id: String?,
        var service_type: List<GetOrderResponse.Response.Service_type>,
        var delivery_type: String?,
        var pickup_day: String?,
        var pickup_date: String?,
        var delivery_day: String?,
        var delivery_date: String?,
        var item_total: String?,
        var delivery_charge: String?,
        var grand_total: String?,
        var user_id: String?,
        var address_id :String?,
        var note: String?,
        var payment_mode: String?,
        var __v: String?
    ) {
        data class Pickup_Time_Slots(

            var start_time: String?,
            var end_time: String?
        )

        data class Delivery_Time_Slots(

            var start_time: String?,
            var end_time: String?
        )

        data class Service_type(
            var items: List<GetOrderResponse.Response.Service_type.Items>,
            var _id: String?,
            var service_type_id: String?,
            var service_name: String?
        )
        {
            data class Items(
                var _id: String,
                var item_id: String?,
                var item_name: String?,
                var quantity: String?,
                var price: String?
            )
        }
    }
}