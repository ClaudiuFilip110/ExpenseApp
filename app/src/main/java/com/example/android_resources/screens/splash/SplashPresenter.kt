package com.example.android_resources.screens.splash

import android.content.Intent
import android.util.Log
import com.example.android_resources.data.database.entities.AutoLoginData
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.login.LoginActivity
import com.example.android_resources.screens.main.MainActivity
import com.example.android_resources.screens.splash.SplashView

class SplashPresenter(private val splashView: SplashView, val userRepository: UserRepository) {
    fun onCreate() {
        autologin()
    }

    fun autologin() {
        val user: AutoLoginData? = userRepository.getAutoLoginUser()
        if (user == null) {
            //go to login
            Log.d("user", "there is no user to make autologin")
            splashView.login()
            return
        }
        //do autologin
        Log.d("user", "autologin user is " + user)
        //in caz ca vrei sa testezi fara autologin decomenteaza linia, da run, comenteaza inapoi si da run iar
//        userRepository.deleteAutoLoginUser()
        splashView.startMain()
    }
}