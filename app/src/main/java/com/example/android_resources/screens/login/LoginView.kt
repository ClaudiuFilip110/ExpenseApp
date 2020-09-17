package com.example.android_resources.screens.login

import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.View
import com.example.android_resources.R
import com.example.android_resources.screens.forgot.ForgotActivity
import com.example.android_resources.screens.main.MainActivity
import com.example.android_resources.screens.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.view.*
import android.util.Patterns
import android.widget.Toast
import com.example.android_resources.data.database.entities.User
import java.util.regex.Pattern


class LoginView(private val activity: LoginActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_login, null)

    init {
        set_login()
        register()
        forgot()
    }

    fun set_login() {
        layout.login_button.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                    val user = User()
                    user.email = layout.login_email_text.text.toString()
                    user.password = layout.login_password_text.text.toString()
                    activity.sendUserToActivity(user)
            } else {
                incorrectCredentials()
            }
        }
    }

    fun register() {
        layout.login_register.setOnClickListener {
            RegisterActivity.start(activity)
        }
    }

    fun forgot() {
        layout.login_forgot.setOnClickListener {
            ForgotActivity.start(activity)
        }
    }

    private fun validatePassword(): Boolean {
        var password: String? = layout.login_password_text.text.toString()
        val PASSWORD_PATTERN: String = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,}$";
        if (password.isNullOrBlank()) {
            return false
        }
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        if (!pattern.matcher(password).matches())
            return false
        return true
    }

    fun validateEmail(): Boolean {
        var email: String? = layout.login_email_text.text.toString()
        if (email.isNullOrEmpty()) {
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return false
        return true
    }

    fun incorrectCredentials() {
        Toast.makeText(activity.baseContext, "Incorrect email or password", Toast.LENGTH_LONG).show()
        layout.login_email_layout.error = ""
        layout.login_password_layout.error = ""
    }

    fun login() {
        activity.login()
    }
}