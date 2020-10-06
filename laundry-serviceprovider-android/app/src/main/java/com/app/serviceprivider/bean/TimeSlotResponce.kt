package com.app.serviceprivider.bean

data class TimeSlotResponce(

    var message: String?,

    var response:List<Response>?
) {
    data class Response(

        var _id: String?,

        var time_slots: List<TimeSlotResponce.Response.TimeSlots>?,

        var day: String?,

        var slot_type: String?,

        var seller_id: String?,

        var __v: String?
    )
    {
        data class TimeSlots(

            var _id: String?,

            var start_time: String?,

            var end_time: String?
        )
    }
}