package com.example.android_resources.screens.converter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_resources.screens.main.MainActivity
import com.example.android_resources.screens.main.MainPresenter
import com.example.android_resources.screens.main.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf

class ConverterActivity : AppCompatActivity(), KoinComponent {
    private val view: ConverterView by inject { parametersOf(this) }
    private val presenter: ConverterPresenter by inject { parametersOf(view, GlobalContext.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ConverterActivity::class.java)
            activity.startActivity(intent)
        }
    }
}