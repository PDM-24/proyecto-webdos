package com.example.profile.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "restaurants")
data class Restaurant(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    val priceRange: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val openingHours: String,
    val averageRating: Float,
    val imageUrl: String
)
data class RestaurantWithComments(
    @Embedded val restaurant: Restaurant,
    @Relation(
        parentColumn = "id",
        entityColumn = "restaurantId"
    )
    val comments: List<Comment>
)
