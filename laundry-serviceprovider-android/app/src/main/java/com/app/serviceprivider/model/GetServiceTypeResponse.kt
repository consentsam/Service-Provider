package com.app.serviceprivider.model

data class GetServiceTypeResponse(

    var message: String?,
    var response: List<Response>?
) {
    data class Response(
        var __v: Int?,
        var _id: String?,
        var image: String?,
        var name: String?
    )
    {

        override fun toString(): String {
            return name!!
        }
    }
}