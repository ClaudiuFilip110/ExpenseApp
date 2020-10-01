package com.example.android_resources.data.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android_resources.data.database.type_converters.DateConverter
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
@Entity(tableName = "Actions")
data class Action(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @TypeConverters(DateConverter::class)
    var date: Date = Date(),
    var amount: Double = 0.0,
    var category: String = "",
    var details: String = "",
    var detailsImage: String = ""
) : Parcelable