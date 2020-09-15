package com.example.android_resources.app.dependecies

import com.example.android_resources.screens.main.MainActivity
import com.example.android_resources.screens.main.MainView
import org.koin.core.module.Module
import org.koin.dsl.module

val viewsModule: Module = module {
    factory { (activity: MainActivity) -> MainView(activity) }
}