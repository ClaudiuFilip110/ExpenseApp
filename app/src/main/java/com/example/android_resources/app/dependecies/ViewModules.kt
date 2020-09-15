package com.example.android_resources.app.dependecies

import com.example.android_resources.screens.main.*
import org.koin.core.module.Module
import org.koin.dsl.module

val viewsModule: Module = module {
    factory { (activity: MainActivity) -> MainView(activity) }
    factory { (activity: SplashActivity) -> SplashView(activity) }
    factory { (activity: LoginActivity) -> LoginView(activity) }
}