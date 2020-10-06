package com.app.serviceprivider.model

data class OnlineDashboardModel (

    var title: String,

    var number: String,

    var image: Int,

    var username: String // Author of blog post


    ) {

        override fun toString(): String {
            return "BlogPost(title='$title', image='$image', username='$username')"
        }

    }