package com.app.serviceprivider.model

class TimeSlotModel(

    var time_slot: String,

    var image: String,

    var username: String


) {

    override fun toString(): String {
        return "BlogPost(title='$time_slot', image='$image', username='$username')"
    }

}