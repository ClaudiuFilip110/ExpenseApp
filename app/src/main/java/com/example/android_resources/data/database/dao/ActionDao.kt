package com.example.android_resources.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.entities.User
import java.time.LocalDateTime
import java.util.*

@Dao
interface ActionDao {
    @Query("SELECT * FROM Actions")
    fun getAll(): List<Action>

    @Query("SELECT * FROM actions WHERE category LIKE :category")
    fun getByCategory(category: String): Action?

    @Insert
    fun insertAction(action: Action)

    @Delete
    fun deleteAction(action: Action)

    @Query("DELETE from Actions")
    fun deleteAll()

    @Query("SELECT id FROM Actions  \n" +
            "ORDER BY id DESC  \n" +
            "LIMIT 1;  ")
    fun getLastId(): Int

    @Query("SELECT SUM(amount) FROM Actions WHERE date <= :date")
    fun getBalanceUntilDate(date: Date): Int

    @Query("SELECT date FROM Actions")
    fun getDates(): List<Date>
}