package com.example.profile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profile.data.database.entity.SearchHistory

@Dao
interface SearchHistoryDAO {
    @Query("SELECT * FROM search_history")
    suspend fun getAllSearchHistory(): List<SearchHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(searchHistory: SearchHistory)

    @Query("SELECT * FROM search_history WHERE userId = :userId")
    suspend fun getSearchHistoryByUserId(userId: Int): List<SearchHistory>
}
