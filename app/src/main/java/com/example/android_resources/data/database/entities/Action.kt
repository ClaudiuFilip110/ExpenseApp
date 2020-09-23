package com.example.android_resources.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android_resources.data.database.type_converters.DateConverter
import java.util.*

@Entity(tableName = "Actions")
class Action(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @TypeConverters(DateConverter::class)
    var date: Date = Date(),
    var amount: Double = 0.0,
    var category: String = "",
    var details: String = "",
    var detailsImage: String = ""
)