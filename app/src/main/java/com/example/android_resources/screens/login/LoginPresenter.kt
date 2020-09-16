package com.example.android_resources.screens.login

import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.data.database.repositories.UserRepository
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginPresenter(private val loginView: LoginView, val userRepository: UserRepository) {
    var user: User = User()
    fun onCreate() {
    }

    fun sendUserToPresenter(user: User) {
        this.user = user
        val userD: User? = userRepository.getUserByMail(user)
        if (userD == null) {
            loginView.incorrectCredentials()
            Log.e("user", "the user doesn't exist in the database")
            return
        }

        if (userD.password == user.password) {
            loginView.login()
        } else {
            loginView.incorrectCredentials()
        }
    }
}