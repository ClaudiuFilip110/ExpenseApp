package com.example.android_resources.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = "",
    var email: String = "",
    var password: String = ""
)