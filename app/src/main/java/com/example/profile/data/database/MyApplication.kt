package com.example.profile.data.database

import android.app.Application
import androidx.room.Room

class MyApplication : Application() {
    companion object{
        lateinit var database: SaborMapDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            SaborMapDatabase::class.java,
            "SaborMapDatabase"
        ).build()
    }
}