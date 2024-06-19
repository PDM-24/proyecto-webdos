package com.example.profile.data.api

import com.example.profile.utils.Constants
import com.google.gson.annotations.SerializedName

data class ApiResponseSuccesful (
    @SerializedName(value = Constants.RESPONSE_SUCCESFUL)
    val ok: Boolean,
    val user: User
)

data class User(
    val _id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String
)

data class ApiResponseError (
    @SerializedName(value = Constants.RESPONSE_ERROR)
    val ok: Boolean,
    val error: String,
    val errors: List<ValidationError>? = null // errores de validacion
)

data class ValidationError(
    val msg: String,
    val param: String,
    val location: String
)