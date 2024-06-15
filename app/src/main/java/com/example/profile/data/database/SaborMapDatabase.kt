package com.example.profile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.profile.data.database.dao.CommentDAO
import com.example.profile.data.database.dao.FavoriteDAO
import com.example.profile.data.database.dao.ImageDAO
import com.example.profile.data.database.dao.OpeningStateDAO
import com.example.profile.data.database.dao.RestaurantDAO
import com.example.profile.data.database.dao.SearchHistoryDAO
import com.example.profile.data.database.dao.UserDAO
import com.example.profile.data.database.entity.Comment
import com.example.profile.data.database.entity.Favorite
import com.example.profile.data.database.entity.Image
import com.example.profile.data.database.entity.OpeningState
import com.example.profile.data.database.entity.Restaurant
import com.example.profile.data.database.entity.SearchHistory
import com.example.profile.data.database.entity.User

@Database(entities = [Restaurant::class,
    Comment::class,
    User::class,
    Favorite::class,
    OpeningState::class,
    Image::class,
    SearchHistory::class], version = 1)
abstract class SaborMapDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDAO
    abstract fun commentDao(): CommentDAO
    abstract fun userDao(): UserDAO
    abstract fun favoriteDao(): FavoriteDAO
    abstract fun openingStateDao(): OpeningStateDAO
    abstract fun imageDao(): ImageDAO
    abstract fun searchHistoryDao(): SearchHistoryDAO
}