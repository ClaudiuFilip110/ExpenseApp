package com.example.android_resources.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), KoinComponent {
    private val view: MainView by inject { parametersOf(this) }
    private val presenter: MainPresenter by inject { parametersOf(view, get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
