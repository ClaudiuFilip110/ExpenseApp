package com.example.android_resources.screens.register

import com.example.android_resources.screens.splash.SplashView

class RegisterPresenter(private val registerView: RegisterView) {
    fun onCreate() {
        updateBackToLogin()
        updateRegister()
    }

    fun updateBackToLogin() {
        registerView.backToLogin()
    }

    fun updateRegister() {
        registerView.register()
    }

}