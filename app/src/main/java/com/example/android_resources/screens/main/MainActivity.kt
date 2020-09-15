package com.example.android_resources.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), KoinComponent {
    private val view: MainView by inject { parametersOf(this) }
    private val presenter: MainPresenter by inject { parametersOf(view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }
}
