package com.app.serviceprivider.webservice


import com.app.serviceprivider.Constants
import com.app.serviceprivider.bean.*
import com.app.serviceprivider.model.*


import io.reactivex.Observable
import okhttp3.RequestBody

import retrofit2.http.*



interface
ApiInterface {

    @FormUrlEncoded
    @POST(Constants.SIGNUP)
    fun signUp(
        @Field("country") country: String,
        @Field("mobileNumber") mobileNumber: String,
        @Field("email") email: String,
        @Field("city") city: String,
        @Field("deviceType") deviceType: String,
        @Field("deviceToken") deviceToken: String?,
        @Field("latitude") latitude: String?,
        @Field("longitude") longitude: String?,
        @Field("deviceId") deviceId: String?,
        @Field("password") password: String,
        @Field("countryCode") countryCode: String,
        @Field("service_provide") service_provide: String

    ): Observable<LoginResponse>

    @FormUrlEncoded
    @POST(Constants.VERIFY_OTP)
    fun verifyOtp(
        @Header("access_token") access_token: String,
        @Field("verification_code") verification_code: String

    ): Observable<LoginResponse>

    @FormUrlEncoded
    @POST(Constants.LOGIN)
    fun login(

        @Field("mobileNumber") mobileNumber: String,
        @Field("deviceType")deviceType: String,
        @Field("deviceToken") deviceToken: String,
        @Field("latitude") latitude: String?,
        @Field("longitude") longitude: String?,
        @Field("password") password: String?,
        @Field("countryCode") countryCode: String?

    ): Observable<LoginResponse>
    @FormUrlEncoded
    @POST(Constants.FORGET_PASSWORD)
    fun forgetPassword(
        @Field("mobileNumber") mobileNumber: String,
        @Field("countryCode") countryCode: String
    ): Observable<LoginResponse>

    @FormUrlEncoded
    @POST(Constants.RESET_PASSWORD)
    fun resetPassword(
        @Header("access_token") access_token: String,
        @Field("password") password: String

    ): Observable<LoginResponse>

    @FormUrlEncoded
    @POST(Constants.PROFILE_CREATION)
    fun profile_creation(
        @Header("access_token") access_token: String,
        @Field("laudary_name") laudary_name: String,
        @Field("owner_name") owner_name: String,
        @Field("mobileNumber") mobileNumber: String,
        @Field("email") email: String,
        @Field("countryCode") countryCode: String,
        @Field("address") address: String,
        @Field("delivery_mobileNumer") delivery_mobileNumer: String
    ): Observable<ProfileResponse>


    @FormUrlEncoded
    @POST(Constants.BANK_DETAIL)
    fun bank_detail(
        @Header("access_token") access_token: String,
        @Field("bank_name") bank_name: String,
        @Field("bank_account_number") bank_account_number: String,
        @Field("account_holder_name") account_holder_name: String,
        @Field("iban_code") iban_code: String
    ): Observable<ProfileResponse>


    @Multipart

    @POST(Constants.UPLOAD_DOCUMENT)
    fun upload_document(
        @Header("access_token") access_token: String,
        @Part("commercial_registation") commercial_registation: RequestBody,
        @Part("municipality_license") municipality_license: RequestBody,
        @Part("tax_registation_number") tax_registation_number: RequestBody,
        @Part("other_document") other_document: RequestBody,
        @Part("address_proof") address_proof: RequestBody
    ): Observable<ProfileResponse>


    @FormUrlEncoded
    @POST(Constants.ONLINE_DASHBOARD)
    fun getOnlineDash(
        @Header("access_token") access_token: String,
        @Field("bank_name") bank_name: String
    ): Observable<OnlineDashboardModel>

    @GET(Constants.GET_SERVICE_TYPE)
    fun getServiceType(

    ): Observable<ServiceTypeResponse>


    @FormUrlEncoded
    @POST(Constants.GET_SERVICE_ITEM)
    fun getServiceItem(
        @Header("access_token") access_token: String,
        @Field("service_type") service_type: String
    ): Observable<ServiceItemResponse>

    @GET(Constants.GET_CATEGORY_ITEM)
    fun getCategoryItem(
    ): Observable<GetCategoryResponse>

    @POST(Constants.GET_Service)
    fun getAddServiceType(

    ): Observable<GetServiceTypeResponse>

    @FormUrlEncoded
    @POST(Constants.GET_NAME_ITEM)
    fun getItemName(
        @Field("catId") catId: String
    ): Observable<GetItemNameResponse>

    @FormUrlEncoded
    @POST(Constants.ADD_SERVICE)
    fun addService(
        @Header("access_token") access_token: String,
        @Field("service_type") service_type: String,
        @Field("category") category: String,
        @Field("item_id") item_id: String,
        @Field("price") price: String
    ): Observable<AddServiceResponse>

    @FormUrlEncoded
    @POST(Constants.ADD_SERVICE)
    fun addEditService(
        @Header("access_token") access_token: String,
        @Field("service_type") service_type: String,
        @Field("category") category: String,
        @Field("item_id") item_id: String,
        @Field("id") id: String,
        @Field("price") price: String
    ): Observable<AddServiceResponse>

    @FormUrlEncoded
    @POST(Constants.DELETE_SERVICE_ITEM)
    fun getDeleteItem(
        @Field("serviceId") serviceId: String
    ): Observable<DeleteServiceResponse>

    @FormUrlEncoded
    @POST(Constants.CHANGE_SERVICE_STATUS)
    fun getServiceStatus(
        @Field("serviceId") serviceId: String,
        @Field("is_service_active") is_service_active: String
    ): Observable<ServiceStatusResponse>

    @FormUrlEncoded
    @POST(Constants.SET_DELIVERY_CHARGE)
    fun setDeliveryCharge(
        @Header("access_token") access_token: String,
        @Field("type") type: String,
        @Field("normal_delivery_charge") normal_delivery_charge: String,
        @Field("express_delivery_charge") express_delivery_charge: String,
        @Field("days_for_normal") days_for_normal: String,
        @Field("days_for_express") days_for_express: String,
        @Field("zero_to_four") zero_to_four: String,
        @Field("four_to_eight") four_to_eight: String,
        @Field("eight_to_twelve") eight_to_twelve: String,
        @Field("minimum_order_charges") minimum_order_charges: String,
        @Field("maximum_order_charges") maximum_order_charges: String
    ): Observable<SetDeliveryResponse>

    @GET(Constants.GET_DELIVERY_CHARGE)
    fun getDeliveryCharge(
        @Header("access_token") access_token: String?
    ): Observable<GetDeliveryResponse>

   @FormUrlEncoded
    @POST(Constants.ADD_TIME_SLOT)
    fun getAddTimeSlot(
       @Header("access_token") access_token: String,
       @Field("time_slots") time_slots: String,
       @Field("day") day: String,
       @Field("slot_type") slot_type: String
    ): Observable<AddTimeSlotResponce>

    @FormUrlEncoded
    @POST(Constants.GET_TIME_SLOT)
    fun getGetTimeSlot(
        @Header("access_token") access_token: String,

        @Field("day") day: String,
        @Field("slot_type") slot_type: String
    ): Observable<TimeSlotResponce>

    @FormUrlEncoded
    @POST(Constants.GET_ORDER)
    fun getOrder(
        @Header("access_token") access_token: String,
        @Field("type") type: String
    ): Observable<GetOrderResponse>

    @FormUrlEncoded
    @POST(Constants.ORDER_CHANGE_STATUS)
    fun getOrderChangeStatus(
        @Header("access_token") access_token: String,
        @Field("status") status: String,
        @Field("order_id") order_id: String
    ): Observable<GetOrderResponse>



}
