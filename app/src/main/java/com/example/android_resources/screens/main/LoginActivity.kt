package com.example.android_resources.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), KoinComponent {
    private val view: LoginView by inject { parametersOf(this) }
    private val presenter: LoginPresenter by inject { parametersOf(view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        presenter.passwordAsterix()
    }

    override fun onResume() {
        super.onResume()
    }
}