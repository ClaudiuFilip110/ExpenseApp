package com.example.android_resources.data.database.repositories

import android.util.Log
import com.example.android_resources.data.database.AppDatabase
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.entities.AutoLoginData
import com.example.android_resources.data.database.entities.User

class UserRepository(private val db: AppDatabase) {
    //-------------------------------------------------------USER
    fun insertUser(user: User) {
        db.userDao().insertUser(user)
    }

    fun getUserByMail(user: User): User? {
        return db.userDao().getByEmail(user.email)
    }

    fun getUserByMailAndPassword(user: User): User? {
        return db.userDao().getByEmailAndPass(user.email, user.password)
    }

    fun getUsers(): ArrayList<User> {
        val users: ArrayList<User> = ArrayList()
        for (user: User in db.userDao().getAll()) {
            users.add(user)
        }
        return users
    }

    fun deleteUsers() {
        db.userDao().deleteAll()
    }

    //----------------------------------------------AUTOLOGINDATA
    fun getAutoLoginUser(): AutoLoginData? {
        return db.autoLoginDao().getUser()
    }

    fun insertAutoLoginUser(autoLoginData: AutoLoginData) {
        db.autoLoginDao().insertUser(autoLoginData)
    }

    fun deleteAutoLoginUser() {
        db.autoLoginDao().deleteUser()
    }

    //-----------------------------------------------------ACTION
    fun getActions(): ArrayList<Action> {
        val actions = ArrayList<Action>()
        for(action: Action in db.actionDao().getAll())
            actions.add(action)
        return actions
    }

    fun getActionByCat(category: String): Action? {
        return db.actionDao().getByCategory(category)
    }

    fun insertAction(action: Action) {
        db.actionDao().insertAction(action)
    }

    fun deleteAction(action: Action) {
        db.actionDao().deleteAction(action)
    }

    fun deletAllActions() {
        db.actionDao().deleteAll()
    }
}