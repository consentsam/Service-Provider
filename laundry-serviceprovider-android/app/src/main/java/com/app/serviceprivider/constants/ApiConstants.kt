package com.app.serviceprivider.constants

class ApiConstants {
    companion object{

        const val BASE_URL = "http://staging.fluper.in:3536/user/"
//      const val BASE_URL1 = "http://15.206.8.58:3000/user/"
//      const val BASE_URL1 = "http://13.127.235.199/Matajer/api/"
        const val IMAGE_URL = "http://54.167.205.226:3000"
        const val REGISTER_USER = "signup"
        const val Login_user="user.php?apicall=login"
        const val Login_SOCIAL="user/social_user_exist"
        const val FORGOT_PASSWORD ="user.php?apicall=forgotpassword"
        const val RESET_PASSWORD = "user.php?apicall=resetpassword"
        const val Change_password = "user/change_password"
        const val Verify_Otp = "user.php?apicall=otpverification"
        const val Resend_Otp = "user.php?apicall=otpresend"
        const val Edit_Profile = "user/edit_profile"
        const val ADD_NEW_ADDRESS = "user/add_new_address"
        const val EDIT_ADDRESS = "user/edit_address"
        const val GET_SAVE_ADDRESS = "user/get_user_address"
        const val PRIVACY_POLICY = "user/privacy_policy"
        const val LEGAL_VALUE = "user/legal"
        const val USER_LOGOUT = "user/logout"
        const val DELETE_ADDRESS = "user/delete_address"
        const val GET_EDIT_ADDRESS = "user/get_single_address"
        const val USER_HOME_ALL_STORE_DETAIL = "user/homepage"
        const val STORE_BY_CATEGORY_ID = "user/store_by_category"
        const val STORE_DETAIL = "user/get_store_detail"
        const val CHANGE_USER_STATE = "user/change_state"
        const val CREATE_ORDER_REQUEST = "user/create_order"
        const val SEARCH_STORE_BY_NAME = "user/search_store_by_name"
        const val GOOGLE_DIRECTION_API = "https://maps.googleapis.com/maps/api/directions/json"
        const val IMAGE = "Chef/indexImage"
        const val DUTY_STATUS = "user/set_duty_status"
        const val CRETE_ORDER_HOME_TO_HOME = "user/create_order_home_to_home"
        const val GET_DRIVER_NEW_REQUEST = "user/new_order_requests"
        const val QUOTE_OFFER_FOR_DELIVERY_CHARGE = "user/quote_an_offer"
        const val REJECT_ORDER_FROM_DRIVER_SIDE = "user/reject_offer"
        const val UPLOAD_MULTIPLE_IMAGES = "user/upload_files"
        const val CATEGORY_LIST = "user/category_listing"
        const val UPLOAD_FILES = "upload_files"


    }

}