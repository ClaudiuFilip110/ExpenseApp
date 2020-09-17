package com.example.android_resources.data.database.repositories

import android.util.Log
import com.example.android_resources.data.database.AppDatabase
import com.example.android_resources.data.database.entities.User

class UserRepository(private val db: AppDatabase) {
    fun insertUser(user: User) {
        db.userDao().insertUser(user)
    }

    fun getUserByMail(user: User): User? {
        val returnedUser: User = db.userDao().getByEmail(user.email) ?: return null
        return returnedUser
    }

    fun getUsers(): ArrayList<User> {
        val users: ArrayList<User> = ArrayList<User>()
        for (user: User in db.userDao().getAll()) {
            users.add(user)
        }
        return users
    }
}