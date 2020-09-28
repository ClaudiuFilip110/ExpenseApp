package com.example.android_resources.screens.forgot

import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.User
import kotlinx.android.synthetic.main.activity_forgot.view.*
import kotlinx.android.synthetic.main.activity_login.view.*

class ForgotView(private val activity: ForgotActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_forgot, null)

    init {
        initPassReset()
    }

    private fun initPassReset() {
        layout.forgot_button.setOnClickListener {
            if (validateEmail()) {
                var user = User()
                user.email = layout.forgot_email_text.text.toString()
                activity.sendEmailToActivity(user)
            } else {
                layout.forgot_email_layout.requestFocus()
                layout.forgot_email_layout.error = "Invalid email address!"
                Toast.makeText(activity.baseContext, "Invalid email address!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun reset() {
        Toast.makeText(activity.baseContext, "An email has been sent!", Toast.LENGTH_SHORT)
            .show()
        activity.finish()
    }

    fun validateEmail(): Boolean {
        var email: String? = layout.forgot_email_text.text.toString()
        if (email.isNullOrEmpty()) {
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return false
        return true
    }

    fun userNotFound() {
        Toast.makeText(activity.baseContext, "User not found", Toast.LENGTH_LONG).show()
        Log.d("user", "the user doesn't exist in the database")
    }
}