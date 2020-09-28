package com.example.android_resources.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_resources.screens.login.LoginActivity
import com.facebook.stetho.Stetho
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), KoinComponent {
    private val view: SplashView by inject { parametersOf(this) }
    private val presenter: SplashPresenter by inject { parametersOf(view, get()) }
    private var str: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        presenter.onCreate()
        Stetho.initializeWithDefaults(this)
    }

    override fun onResume() {
        super.onResume()
    }
}