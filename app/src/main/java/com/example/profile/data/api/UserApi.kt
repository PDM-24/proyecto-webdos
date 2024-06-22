package com.example.profile.data.api

import com.example.profile.utils.Constants
import com.google.gson.annotations.SerializedName

data class UserApi (
    @SerializedName(value = Constants.USER_ID)
    val id: String? = null,

    @SerializedName(value = Constants.USER_FIRST_NAME)
    val firstName: String = "",

    @SerializedName(value = Constants.USER_LAST_NAME)
    val lastName: String = "",

    @SerializedName(value = Constants.USER_EMAIL)
    val email: String = "",

    @SerializedName(value = Constants.USER_NAME)
    val username: String = "",

    @SerializedName(value = Constants.USER_PASSWORD)
    val password: String = "",

    @SerializedName(value = Constants.USER_CONFIRM_PASSWORD)
    val confirmPassword: String = "",
)



