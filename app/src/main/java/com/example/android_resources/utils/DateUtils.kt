package com.example.android_resources.utils

import android.util.Log
import com.example.android_resources.data.database.entities.Action
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        fun convertDate(date: Date): LocalDateTime {
            val pattern = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val d = pattern.format(date)
            return LocalDateTime.parse(d)
        }

        fun convertSimpleDate(date: Date): LocalDate {
            val pattern = SimpleDateFormat("yyyy-MM-dd")
            val d = pattern.format(date)
            return LocalDate.parse(d)
        }
    }
}