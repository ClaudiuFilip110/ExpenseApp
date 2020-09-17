package com.example.android_resources.screens.login

import android.util.Log
import com.example.android_resources.data.database.entities.AutoLoginData
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.data.database.repositories.UserRepository

class LoginPresenter(private val loginView: LoginView, val userRepository: UserRepository) {
    var userInput: User = User()
    fun onCreate() {
    }

    fun sendUserToPresenter(user: User) {
        this.userInput = user
        val userD: User? = userRepository.getUserByMail(user)
        if (userD == null) {
            loginView.incorrectCredentials()
            Log.e("user", "the user doesn't exist in the database")
            return
        }
        Log.d("user", userD.toString())
        Log.d("user", user.toString())
        if (userD.password == user.password) {
            loginView.login()
            //add to AutoLoginDatabase
            addToAutoLoginDB(userD)
        } else {
            loginView.incorrectCredentials()
        }
    }

    private fun addToAutoLoginDB(user: User) {
        var getUser = userRepository.getUserByMail(user) ?: return
        Log.d("user", "getUser is " + getUser.toString())
        var autologin = AutoLoginData()
        autologin.email = getUser.email
        autologin.name = getUser.name
        autologin.password = getUser.password
        userRepository.insertAutoLoginUser(autologin)
    }
}