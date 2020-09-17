package com.example.android_resources.data.database.dao

import androidx.room.*
import com.example.android_resources.data.database.entities.AutoLoginData
import com.example.android_resources.data.database.entities.User

@Dao
interface AutoLoginDao {
    @Query("SELECT * FROM AutoLoginData WHERE id = 0")
    fun getUser(): AutoLoginData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(AutoLoginData: AutoLoginData)

    @Query("DELETE from AutoLoginData")
    fun deleteUser()
}