package com.app.serviceprivider.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharePrefUtils

    (val context: Context) {

    private var PREF_KEY_IS_LOGIN_SUCCESSFUL = "PREF_KEY_IS_LOGIN_SUCCESSFUL"

    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    private var editor: SharedPreferences.Editor? = null

    companion object {
        var sharePrefInstance: SharePrefUtils? = null
        fun getSharedInstance(context: Context): SharePrefUtils {
            if (sharePrefInstance == null) {
                sharePrefInstance = SharePrefUtils(context)
            }
            return sharePrefInstance!!
        }
    }

    var is_login_successful: Boolean
        get() = sharedPreferences!!.getBoolean(PREF_KEY_IS_LOGIN_SUCCESSFUL, false)
        set(value) = setValues(PREF_KEY_IS_LOGIN_SUCCESSFUL, value)

    var homeLogin: Int
        get() = sharedPreferences!!.getInt("homeLogin", 0)
        set(value) = setValues("homeLogin", value)

    var city: String?
        get() = sharedPreferences.getString("city", "")
        set(value) = setValues("city", value)
    var lat: String?
        get() = sharedPreferences.getString("lat", "")
        set(value) = setValues("lat", value)
    var long: String?
        get() = sharedPreferences.getString("long", "")
        set(value) = setValues("long", value)
    var countryCode: String?
        get() = sharedPreferences.getString("countryCode", "")
        set(value) = setValues("countryCode", value)
    var pin: String?
        get() = sharedPreferences.getString("pin", "")
        set(value) = setValues("pin", value)

    var accessToken: String?
        get() = sharedPreferences.getString("accessToken", "")
        set(value) = setValues("accessToken", value)

    var userName: String?
        get() = sharedPreferences.getString("userName", "")?:""
        set(value) = setValues("userName", value)

    var userEmail: String?
        get() = sharedPreferences.getString("userEmail", "")?:""
        set(value) = setValues("userEmail", value)

    var userMobile: String?
        get() = sharedPreferences.getString("userMobile", "")?:""
        set(value) = setValues("userMobile", value)

    var mobileNumber: String?
        get() = sharedPreferences.getString("mobileNumber", "")
        set(value) = setValues("mobileNumber", value)
    var name: String?
        get() = sharedPreferences.getString("name", "")
        set(value) = setValues("name", value)
    var email: String?
        get() = sharedPreferences.getString("email", "")
        set(value) = setValues("email", value)
    var gender: String?
        get() = sharedPreferences.getString("gender", "")
        set(value) = setValues("gender", value)
    var Country: String?
        get() = sharedPreferences.getString("Country", "")
        set(value) = setValues("Country", value)
    var profile_pic: String?
        get() = sharedPreferences.getString("profile_pic", "")
        set(value) = setValues("profile_pic", value)
    var createdAt: String?
        get() = sharedPreferences.getString("createdAt", "")
        set(value) = setValues("createdAt", value)
    var updatedAt: String?
        get() = sharedPreferences.getString("updatedAt", "")
        set(value) = setValues("updatedAt", value)
    var loc: Boolean
        get() = sharedPreferences.getBoolean("loc", false)
        set(value) = setValues("loc", value)

    var compareListModifiedOn: String?
        get() = sharedPreferences.getString("compareListModifiedOn", " ")
        set(value) = setValues("compareListModifiedOn", value)

    // device_token
    var device_token: String?
        get() = sharedPreferences.getString("device_token", " ")
        set(value) = setValues("device_token", value)

    var device_id: String?
        get() = sharedPreferences.getString("device_id", " ")
        set(value) = setValues("device_id", value)

    var selectlang: Boolean
        get() = sharedPreferences.getBoolean("selectlang", false)
        set(value) = setValues("selectlang", value)


    // BASE_URL_PRODUCT

    private fun setValues(key: String, value: Any?) {
        if (value != null) {
            setDefaultPref()
            setEditor()
            if (editor != null) {
                when (value) {
                    is String -> {
                        editor!!.putString(key, value)
                    }
                    is Int -> {
                        editor!!.putInt(key, value)
                    }
                    is Boolean -> {
                        editor!!.putBoolean(key, value)
                    }
                }
                editor!!.apply()
            } else
                println("Saving fail : " + key)
        }
    }

    private fun setDefaultPref() {
        if (sharedPreferences == null)
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun setEditor() {
        if (sharedPreferences != null)
            if (editor == null)
                editor = sharedPreferences!!.edit()
    }

    fun deletePreferences() {
        sharedPreferences.edit().clear().apply()
    }

    /* *************************************************
    * *************** SAVE DATA METHOD ****************
    * *************************************************/

}

