package com.example.profile.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profile.data.database.entity.OpeningState

@Dao
interface OpeningStateDAO {
    @Query("SELECT * FROM opening_states")
    suspend fun getAllOpeningStates(): List<OpeningState>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOpeningState(openingState: OpeningState)

    @Query("SELECT * FROM opening_states WHERE restaurantId = :restaurantId")
    suspend fun getOpeningStateByRestaurantId(restaurantId: Int): List<OpeningState>
}
