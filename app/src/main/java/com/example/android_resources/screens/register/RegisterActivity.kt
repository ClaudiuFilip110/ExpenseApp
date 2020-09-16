package com.example.android_resources.screens.register

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_resources.screens.login.LoginActivity
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class RegisterActivity : AppCompatActivity(), KoinComponent {
    private val view: RegisterView by inject { parametersOf(this) }
    private val presenter: RegisterPresenter by inject { parametersOf(view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        presenter.onCreate()
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, RegisterActivity::class.java)
            activity.startActivity(intent)
        }
    }
}