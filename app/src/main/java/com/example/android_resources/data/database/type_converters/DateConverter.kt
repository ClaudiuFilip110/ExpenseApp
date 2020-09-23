package com.example.android_resources.data.database.type_converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
        @TypeConverter
        fun toDate(dateLong: Long): Date {
            return Date(dateLong)
        }

        @TypeConverter
        fun fromDate(date: Date): Long {
            return date.time
        }
}