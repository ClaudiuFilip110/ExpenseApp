package com.example.android_resources.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_resources.data.database.dao.ActionDao
import com.example.android_resources.data.database.dao.AutoLoginDao
import com.example.android_resources.data.database.dao.UserDao
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.entities.AutoLoginData
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.data.database.type_converters.DateConverter
import com.example.android_resources.utils.Constants

@Database(entities = [User::class, AutoLoginData::class, Action::class], version = Constants.DATABASE_VERSION)

@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun autoLoginDao(): AutoLoginDao
    abstract fun actionDao(): ActionDao
}
