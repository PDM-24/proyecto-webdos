package com.example.profile.data.api

import com.google.gson.annotations.SerializedName

data class CommentsApi(

    @SerializedName("_id")
    val id: Id,

    @SerializedName("comentario")
    val comentario: String = "",

    @SerializedName("hidden")
    val hidden: Boolean = false,

    @SerializedName("username")
    val username: Id,

    @SerializedName("Me_gusta")
    val meGusta: List<Any> = emptyList(),

    @SerializedName("comentarios")
    val comentarios: List<Any> = emptyList(),

    @SerializedName("createdAt")
    val createdAt: DateWrapper,

    @SerializedName("updatedAt")
    val updatedAt: DateWrapper,

    @SerializedName("__v")
    val v: Int = 0
)

data class Id(
    @SerializedName("\$objectId")
    val objectId: String? = null
)

data class DateWrapper(
    @SerializedName("\$date")
    val date: String? = null
)
