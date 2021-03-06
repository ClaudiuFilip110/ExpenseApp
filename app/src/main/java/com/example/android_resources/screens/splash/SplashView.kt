package com.example.android_resources.screens.splash

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android_resources.R
import com.example.android_resources.screens.login.LoginActivity
import com.example.android_resources.screens.main.MainActivity

class SplashView(private val activity: SplashActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_splash, null)

    fun login() {
        val timer: Thread = object : Thread() {
            override fun run() {
                try {
                    //Display for 3 seconds
                    sleep(3000)
                } catch (e: InterruptedException) {
                    Log.e("splash", "splash can't start")
                    e.printStackTrace()
                } finally {
                    LoginActivity.start(activity)
                    activity.finish()
                }
            }
        }
        timer.start()
    }

    fun startMain() {
        MainActivity.start(activity)
        activity.finish()
    }
}