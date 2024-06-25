package com.example.profile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profile.data.database.entity.Comment

@Dao
interface CommentDAO {
    @Query("SELECT * FROM comments")
    suspend fun getAllComments(): List<Comment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: Comment)

    @Query("SELECT * FROM comments WHERE restaurantId = :restaurantId")
    suspend fun getCommentsByRestaurantId(restaurantId: Int): List<Comment>
}
