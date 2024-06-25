package com.example.profile.data.database

import android.app.Application
import androidx.room.Room
import com.google.android.libraries.places.api.Places

class MyApplication : Application() {
    companion object {
        lateinit var database: SaborMapDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            SaborMapDatabase::class.java,
            "SaborMapDatabase"
        ).build()

        Places.initialize(applicationContext, "AIzaSyDCAR5iX4iNFYHsHx1sew4Os51dO8geS2A")


    }
}
/*override fun onAPICreate() {
        super.onCreate()
        // Inicializa Google Places API
        Places.initialize(applicationContext, "YOUR_API_KEY")
    }
}*/
