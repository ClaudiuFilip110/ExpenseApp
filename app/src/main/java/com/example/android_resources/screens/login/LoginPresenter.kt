package com.example.android_resources.screens.login

import android.os.Build
import android.util.Log
import com.example.android_resources.data.database.entities.AutoLoginData
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.data.preferences.Preferences
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class LoginPresenter(private val loginView: LoginView, val userRepository: UserRepository) {
    var userInput: User = User()
    fun onCreate() {
    }

    fun sendUserToPresenter(user: User) {
        this.userInput = user
//        Log.d("user","USERS-------------------")
//        for (userr: User in userRepository.getUsers()) {
//            Log.d("user",userr.toString())
//        }
        val userD: User? = userRepository.getUserByMailAndPassword(user)
        if (userD == null) {
            loginView.incorrectCredentials()
            Log.e("user", "the user doesn't exist in the database")
            return
        }
        loginView.login()
        //add to AutoLoginDatabase
        addToAutoLoginDB(userD)
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

    fun deleteUsers() {
        userRepository.deleteUsers()
    }
}