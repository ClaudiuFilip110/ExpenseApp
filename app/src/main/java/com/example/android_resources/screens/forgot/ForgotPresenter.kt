package com.example.android_resources.screens.forgot

import android.util.Log
import android.widget.Toast
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.data.database.repositories.UserRepository

class ForgotPresenter(val forgotView: ForgotView, val userRepository: UserRepository) {
    fun onCreate() {}

    fun sendEmailToPresenter(user: User) {
        val userD: User? = userRepository.getUserByMail(user)
        if (userD == null) {
            forgotView.userNotFound()
            return
        }
        forgotView.reset()
    }
}