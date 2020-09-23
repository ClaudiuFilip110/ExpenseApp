package com.example.android_resources.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.entities.User

@Dao
interface ActionDao {
    @Query("SELECT * FROM Actions")
    fun getAll(): List<Action>

    @Query("SELECT * FROM actions WHERE category LIKE :category")
    fun getByEmail(category: String?): Action?

    @Insert
    fun insertAction(action: Action)

    @Delete
    fun deleteAction(action: Action)

    @Query("DELETE from Actions")
    fun deleteAll()
}