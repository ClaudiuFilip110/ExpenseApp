package com.example.android_resources.screens.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.screens.main.MainActivity
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), KoinComponent {
    private val view: LoginView by inject { parametersOf(this) }
    private val presenter: LoginPresenter by inject { parametersOf(view, get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
    }

    fun sendUserToActivity(user: User) {
        presenter.sendUserToPresenter(user)
    }

    fun login() {
        MainActivity.start(this)
        finish()
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }
}