package com.example.profile.data.api

import com.google.gson.annotations.SerializedName

/*
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val passwordHash: String
)
 */

data class UserApi (
    @SerializedName(value = "_id")
    val id: Int = 0,

    @SerializedName(value = "name")
    val name: String,

    @SerializedName(value = "email")
    val email: String,

    @SerializedName(value = "passwordHash")
    val passwordHash: String
)

