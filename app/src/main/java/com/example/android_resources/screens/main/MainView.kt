package com.example.android_resources.screens.main

import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.android_resources.R
import com.example.android_resources.data.database.RoomDB
import com.example.android_resources.data.database.entities.User
import com.example.android_resources.data.database.repositories.UserRepository

class MainView(private val activity: MainActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_main, null)

    fun showToastOnResume() {
        Toast.makeText(activity.baseContext, "Test MPV with Koin", Toast.LENGTH_LONG).show()
    }

}