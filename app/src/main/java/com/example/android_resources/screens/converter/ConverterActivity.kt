package com.example.android_resources.screens.converter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConverterActivity : AppCompatActivity(), KoinComponent {
    private val view: ConverterView by inject { parametersOf(this) }
    private val presenter: ConverterPresenter by inject { parametersOf(view, GlobalContext.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        presenter.onCreate()
    }

    fun receiveFromView(s: String, s1: String, s2: String) {
        presenter.receiveFromAct(s, s1, s2)
    }

    fun goBack() {
        finish()
    }

    fun receiveFormattedText(): String {
        return presenter.sendFormattedDate()
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ConverterActivity::class.java)
            activity.startActivity(intent)
        }
    }
}