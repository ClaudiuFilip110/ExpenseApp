package com.example.android_resources.app.dependecies

import com.example.android_resources.screens.forgot.ForgotPresenter
import com.example.android_resources.screens.forgot.ForgotView
import com.example.android_resources.screens.login.LoginPresenter
import com.example.android_resources.screens.login.LoginView
import com.example.android_resources.screens.main.*
import com.example.android_resources.screens.register.RegisterPresenter
import com.example.android_resources.screens.register.RegisterView
import com.example.android_resources.screens.splash.SplashPresenter
import com.example.android_resources.screens.splash.SplashView
import org.koin.core.module.Module
import org.koin.dsl.module

val presentersModules: Module = module {
    factory { (view: MainView) -> MainPresenter(view, get()) }
    factory { (view: SplashView) -> SplashPresenter(view) }
    factory { (view: LoginView) -> LoginPresenter(view, get()) }
    factory { (view: RegisterView) -> RegisterPresenter(view, get()) }
    factory { (view: ForgotView) -> ForgotPresenter(view, get()) }
}