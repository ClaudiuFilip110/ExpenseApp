package com.example.android_resources.screens.register

import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.android_resources.R
import com.example.android_resources.data.Crypto.Crypto
import com.example.android_resources.data.database.entities.User
import kotlinx.android.synthetic.main.activity_register.view.*
import java.util.regex.Pattern

class RegisterView(private val activity: RegisterActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_register, null)

    init {
        register()
    }

    fun backToLogin() {
        layout.register_login.setOnClickListener {
            activity.finishRActivity()
        }
    }

    //TODO: refactor validate functions
    fun register() {
        layout.register_button.setOnClickListener {
            if (validateName()) {
                if (validateEmail()) {
                    if (validatePassword()) {
                        //send user to Activity
                        var user = User()
                        user.name = layout.register_name_text.text.toString()
                        user.email = layout.register_email_text.text.toString()
                        user.password = layout.register_password_text.text.toString()
                        val encPassword = Crypto.encrypt(user.password)
                        if (encPassword != null) {
                            user.password = encPassword
                        }
                        activity.sendUser(user)
                        //back to login
                        activity.finishRActivity()
                    } else {
                        layout.register_password_text.requestFocus()
                        layout.register_password_text.error =
                            "Password must contain at least 1 number and must be at least 4 characters long"
                        Toast.makeText(
                            activity.baseContext,
                            "Incorrect password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    layout.register_email_text.requestFocus()
                    layout.register_email_text.error = "Incorrect email"
                }
            } else {
                layout.register_name_text.requestFocus()
                layout.register_name_text.error = "Incorrect name"
            }
        }
    }

    private fun validateName(): Boolean {
        var name: String? = layout.register_name_text.text.toString()
        //^([a-z]+[,.]?[ ]?|[a-z]+['-]?)+$
        val NAME_PATTERN: String = "\\b([A-Za-z][-,a-z. ']+[ ]*)+";
        if (name.isNullOrBlank()) {
            return false
        }
        val pattern = Pattern.compile(NAME_PATTERN)
        if (!pattern.matcher(name).matches())
            return false
        return true
    }

    private fun validatePassword(): Boolean {
        var password: String? = layout.register_password_text.text.toString()
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
        var email: String? = layout.register_email_text.text.toString()
        if (email.isNullOrEmpty()) {
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return false
        return true
    }

    fun correctInsertion() {
        Toast.makeText(
            activity.baseContext,
            "User has been succesfully inserted",
            Toast.LENGTH_SHORT
        ).show()
    }
}