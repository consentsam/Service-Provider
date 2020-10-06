package com.app.serviceprivider.bean

data class AddTimeSlotResponce(

    var message: String?,

    var response:Response?
) {
    data class Response(

        var _id: String?,

        var time_slots: List<AddTimeSlotResponce.Response.TimeSlots>?,

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