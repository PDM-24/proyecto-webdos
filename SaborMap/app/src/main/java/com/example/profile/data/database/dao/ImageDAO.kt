package com.example.profile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profile.data.database.entity.Image

@Dao
interface ImageDAO {
    @Query("SELECT * FROM images")
    suspend fun getAllImages(): List<Image>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: Image)

    @Query("SELECT * FROM images WHERE restaurantId = :restaurantId")
    suspend fun getImagesByRestaurantId(restaurantId: Int): List<Image>
}
