package com.example.android_resources.app.dependecies

import com.example.android_resources.screens.action.ActionActivity
import com.example.android_resources.screens.action.ActionView
import com.example.android_resources.screens.converter.ConverterActivity
import com.example.android_resources.screens.converter.ConverterView
import com.example.android_resources.screens.forgot.ForgotActivity
import com.example.android_resources.screens.forgot.ForgotView
import com.example.android_resources.screens.login.LoginActivity
import com.example.android_resources.screens.login.LoginView
import com.example.android_resources.screens.main.*
import com.example.android_resources.screens.register.RegisterActivity
import com.example.android_resources.screens.register.RegisterView
import com.example.android_resources.screens.splash.SplashActivity
import com.example.android_resources.screens.splash.SplashView
import org.koin.core.module.Module
import org.koin.dsl.module

val viewsModule: Module = module {
    factory { (activity: MainActivity) -> MainView(activity) }
    factory { (activity: SplashActivity) -> SplashView(activity) }
    factory { (activity: LoginActivity) -> LoginView(activity) }
    factory { (activity: RegisterActivity) -> RegisterView(activity) }
    factory { (activity: ForgotActivity) -> ForgotView(activity) }
    factory { (activity: ConverterActivity) -> ConverterView(activity) }
    factory { (activity: ActionActivity) -> ActionView(activity) }
}