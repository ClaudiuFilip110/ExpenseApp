package com.example.android_resources.screens.main

class LoginPresenter(private val loginView: LoginView) {

    fun passwordAsterix() {
        loginView.setPasswordChar()
    }
}