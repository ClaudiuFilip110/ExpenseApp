package com.example.android_resources.screens.main

import android.util.Log
import com.example.android_resources.data.database.AppDatabase
import com.example.android_resources.data.database.RoomDB
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.data.database.repositories.UserRepository

class MainPresenter(private val mainView: MainView, private val userRepository: UserRepository) {
    fun onCreate() {
        //--------------------------------INSERT USER----------
//        val user: User = User()
//        user.name = "Claudiu Filip"
//        user.email = "w@w.w"
//        user.password = "ww22"
//        userRepository.insertUser(user)

        //--------------------------------GET ALL USERS----------
//        val users: ArrayList<User> = userRepository.getUsers()
//        if (users.size == 0) {
//            Log.d("user", "no users in the db")
//        } else
//            for (user: User in users) {
//                Log.d("user", user.toString())
//            }
    }

    fun onResume() {
        mainView.showToastOnResume()
    }
}