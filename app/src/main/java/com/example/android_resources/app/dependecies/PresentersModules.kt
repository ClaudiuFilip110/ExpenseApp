package com.example.android_resources.app.dependecies

import com.example.android_resources.screens.main.*
import org.koin.core.module.Module
import org.koin.dsl.module

val presentersModules: Module = module {
    factory { (view: MainView) -> MainPresenter(view) }
    factory { (view: SplashView) -> SplashPresenter(view) }
    factory { (view: LoginView) -> LoginPresenter(view) }
}