package com.example.android_resources.screens.forgot

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_resources.data.database.entities.User
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf

class ForgotActivity : AppCompatActivity(), KoinComponent {
    private val view: ForgotView by inject { parametersOf(this) }
    private val presenter: ForgotPresenter by inject { parametersOf(view, get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        presenter.onCreate()
    }

    public fun sendEmailToActivity(user: User) {
        presenter.sendEmailToPresenter(user)
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ForgotActivity::class.java)
            activity.startActivity(intent)
        }
    }
}