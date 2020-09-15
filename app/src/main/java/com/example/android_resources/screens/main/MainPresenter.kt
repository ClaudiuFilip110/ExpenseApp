package com.example.android_resources.screens.main

class MainPresenter(private val mainView: MainView) {

    fun onResume() {
        mainView.showToastOnResume()
    }
}