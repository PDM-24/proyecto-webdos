package com.example.profile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profile.data.database.entity.Favorite

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites WHERE userId = :userId")
    suspend fun getFavoritesByUserId(userId: Int): List<Favorite>
}
