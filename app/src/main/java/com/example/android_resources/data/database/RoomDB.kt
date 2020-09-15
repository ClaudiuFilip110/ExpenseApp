package com.example.android_resources.data.database

import android.content.Context
import androidx.room.Room
import com.example.android_resources.utils.Constants

class RoomDB(context: Context) {
    val appDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}