package com.example.android_resources.data.database.repositories

import android.util.Log
import com.example.android_resources.data.database.AppDatabase
import com.example.android_resources.data.database.entities.AutoLoginData
import com.example.android_resources.data.database.entities.User

class UserRepository(private val db: AppDatabase) {
    fun insertUser(user: User) {
        db.userDao().insertUser(user)
    }

    fun getUserByMail(user: User): User? {
        return db.userDao().getByEmail(user.email) ?: return null
    }

    fun getUsers(): ArrayList<User> {
        val users: ArrayList<User> = ArrayList()
        for (user: User in db.userDao().getAll()) {
            users.add(user)
        }
        return users
    }

    fun getAutoLoginUser(): AutoLoginData? {
        return db.autoLoginDao().getUser() ?: return null
    }

    fun insertAutoLoginUser(autoLoginData: AutoLoginData) {
        db.autoLoginDao().insertUser(autoLoginData)
    }

    fun deleteAutoLoginUser() {
        db.autoLoginDao().deleteUser()
    }
}