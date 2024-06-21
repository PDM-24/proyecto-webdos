package com.example.profile.data.api

import com.example.profile.utils.Constants
import com.google.gson.annotations.SerializedName

data class LoginApi (

    @SerializedName(value = Constants.USER_EMAIL)
    val email: String = "",

    @SerializedName(value = Constants.USER_PASSWORD)
    val password: String = ""

)