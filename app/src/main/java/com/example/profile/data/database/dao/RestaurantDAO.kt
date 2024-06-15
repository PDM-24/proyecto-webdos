package com.example.profile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profile.data.database.entity.Restaurant

@Dao
interface RestaurantDAO {
    @Query("SELECT * FROM restaurants")
    suspend fun getAllRestaurants(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants WHERE id = :id")
    suspend fun getRestaurantById(id: Int): Restaurant
}

// Similar DAO interfaces should be created for other entities.
