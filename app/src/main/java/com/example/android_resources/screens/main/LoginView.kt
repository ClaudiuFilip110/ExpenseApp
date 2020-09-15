package com.example.android_resources.screens.main

import android.view.View
import com.example.android_resources.R
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginView(private val activity: LoginActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_login, null)

    fun setPasswordChar() {
    }
}