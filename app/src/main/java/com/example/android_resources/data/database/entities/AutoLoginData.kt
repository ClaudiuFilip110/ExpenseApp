package com.example.android_resources.data.database.entities

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AutoLoginData")
data class AutoLoginData(
    @PrimaryKey()
    val id: Long = 0,
    var name: String = "",
    var email: String = "",
    var password: String = ""
)