package com.example.android_resources.screens.register

import android.util.Log
import android.widget.Toast
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.splash.SplashView
import java.lang.Exception

class RegisterPresenter(private val registerView: RegisterView, val userRepository: UserRepository) {
    var user = User()
    fun onCreate() {
    }

    fun sendUser(userInput: User) {
        user = userInput
        addToDb()
    }

    fun addToDb() {
        try {
            userRepository.insertUser(user)
            registerView.correctInsertion()
            Log.d("user", "user registered succesfully")
        } catch (e: Exception) {
            Log.e("user", "exception " + e.toString())
        }
    }
}