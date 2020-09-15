package com.example.android_resources.screens.main

import android.view.View
import android.widget.Toast
import com.example.android_resources.R

class MainView(private val activity: MainActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_main, null)

    fun showToastOnResume() {
        Toast.makeText(activity.baseContext, "Test MPV with Koin", Toast.LENGTH_LONG).show()
    }
}