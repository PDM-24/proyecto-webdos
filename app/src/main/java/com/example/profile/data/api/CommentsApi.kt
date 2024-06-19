package com.example.profile.data.api

import com.google.gson.annotations.SerializedName

/*
@Entity(
    tableName = "comments",
    foreignKeys = [ForeignKey(
        entity = Restaurant::class,
        parentColumns = ["id"],
        childColumns = ["restaurantId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val restaurantId: Int,
    val userId: Int,
    val rating: Float,
    val comment: String,
    val timestamp: Long
)
 */

data class CommentsApi(
    @SerializedName(value = "_id")
    val id: Int = 0,

    @SerializedName(value = "restaurantId")
    val restaurantId: Int,

    @SerializedName(value = "userId")
    val userId: Int,

    @SerializedName(value = "rating")
    val rating: Float,

    @SerializedName(value = "comment")
    val comment: String,

    @SerializedName(value = "timestamp")
    val timestamp: Long
)

