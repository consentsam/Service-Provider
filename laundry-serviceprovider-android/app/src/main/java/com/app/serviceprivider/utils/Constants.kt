package com.app.serviceprivider

interface Constants {

    companion object {

        //Patterns
        const val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)\$"
        const val PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[A-Z]).{8,255}\$"


        //Api Constants
        const val BASE_URL = "http://15.207.1.62:3536/"

        const val DEVICE_TYPE_ANDROID = 1
        const val PICKFILE_RESULT_CODE1 = 1
        const val PICKFILE_RESULT_CODE2 = 2
        const val PICKFILE_RESULT_CODE3 = 3
        const val PICKFILE_RESULT_CODE4 = 4
        const val PICKFILE_RESULT_CODE5 = 5
        //User
        const val SIGNUP = "seller/signup"
        const val VERIFY_OTP = "seller/verifyOtp"
        const val RESEND_OTP = "users/resend_otp"
        const val FORGET_PASSWORD = "seller/forgetPassword"
        const val RESET_PASSWORD = "seller/resetPassword"
        const val LOGIN = "seller/login"
        const val PROFILE_CREATION = "seller/createProfile"
        const val UPLOAD_DOCUMENT = "seller/upload_document"
        const val BANK_DETAIL = "seller/bank_details"
        const val ONLINE_DASHBOARD = "seller/bank_details"
        const val GET_SERVICE_TYPE = "seller/get_category"
        const val GET_SERVICE_ITEM = "seller/get_service_items"
        const val GET_CATEGORY_ITEM = "seller/get_category"
        const val GET_Service = "seller/get_serviceType"
        const val GET_NAME_ITEM = "seller/get_item"
        const val ADD_SERVICE = "seller/add_service_item"
        const val DELETE_SERVICE_ITEM = "seller/delete_serviceItem"
        const val CHANGE_SERVICE_STATUS = "seller/changeStatus_serviceItems"
        const val SET_DELIVERY_CHARGE = "seller/set_delivery_charges"
        const val GET_DELIVERY_CHARGE="seller/get_delivery_charges"
        const val ADD_TIME_SLOT="seller/add_time_slot"
        const val GET_TIME_SLOT="seller/get_time_slot"
        const val GET_ORDER="seller/get_orders"
        const val ORDER_CHANGE_STATUS="seller/order_change_status"
    }


}
