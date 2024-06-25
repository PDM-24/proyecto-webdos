package com.example.profile.data.api

import com.google.gson.annotations.SerializedName

data class CommentsApi(

    @SerializedName("_id")
    val id: Id,

    @SerializedName("comentario")
    val comentario: String = ""

)

data class CommentApi(
    @SerializedName("_id")
    val id: String? = null,

    @SerializedName("comentario")
    val comentario: String = "",

    @SerializedName("hidden")
    val hidden: Boolean = false,

    @SerializedName("username")
    val username: String = "",

    @SerializedName("Me_gusta")
    val meGusta: List<String> = listOf(),

    @SerializedName("comentarios")
    val comentarios: List<CommentApi> = listOf(),

    @SerializedName("createdAt")
    val createdAt: String = "",

    @SerializedName("updatedAt")
    val updatedAt: String = "",

    @SerializedName("__v")
    val version: Int = 0
)

data class Id(
    @SerializedName("\$objectId")
    val objectId: String? = null
)

data class DateWrapper(
    @SerializedName("\$date")
    val date: String? = null
)