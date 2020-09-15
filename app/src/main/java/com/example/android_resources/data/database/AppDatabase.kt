package com.example.android_resources.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_resources.data.database.dao.UserDao
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.utils.Constants

@Database(entities = [User::class], version = Constants.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
