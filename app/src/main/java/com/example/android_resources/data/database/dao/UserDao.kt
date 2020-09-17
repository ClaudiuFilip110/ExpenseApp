package com.example.android_resources.data.database.dao

import androidx.room.*
import com.example.android_resources.data.database.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM Users")
    fun getAll(): List<User>

    @Query("SELECT * FROM Users WHERE email LIKE :email")
    fun getByEmail(email: String?): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)
}