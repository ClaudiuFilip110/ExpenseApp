package com.example.android_resources.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), KoinComponent {
    private val view: SplashView by inject { parametersOf(this) }
    private val presenter: SplashPresenter by inject { parametersOf(view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
    }

    override fun onResume() {
        super.onResume()
    }
}