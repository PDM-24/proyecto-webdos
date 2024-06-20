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

    @SerializedName(value = Constants.USER_HASHED_PASSWORD)
    val hashedPassword: String = "",

    @SerializedName(value = "confirmPassword")
    val confirmPassword: String = "",
/*
    @SerializedName(value = Constants.USER_SALT)
    val salt: String? = null,

    @SerializedName(value = Constants.USER_TOKENS)
    val tokens: List<String>? = emptyList()

 */
)



