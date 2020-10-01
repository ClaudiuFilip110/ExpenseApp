package com.example.android_resources.app.dependecies

import com.example.android_resources.screens.action.ActionPresenter
import com.example.android_resources.screens.action.ActionView
import com.example.android_resources.screens.budget.BudgetPresenter
import com.example.android_resources.screens.budget.BudgetView
import com.example.android_resources.screens.converter.ConverterPresenter
import com.example.android_resources.screens.converter.ConverterView
import com.example.android_resources.screens.expenses.*
import com.example.android_resources.screens.expenses.expensesUI.ExpensesUIPresenter
import com.example.android_resources.screens.expenses.expensesUI.ExpensesUIView
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
    factory { (view: SplashView) -> SplashPresenter(view, get()) }
    factory { (view: LoginView) -> LoginPresenter(view, get()) }
    factory { (view: RegisterView) -> RegisterPresenter(view, get()) }
    factory { (view: ForgotView) -> ForgotPresenter(view, get()) }
    factory { (view: ConverterView) -> ConverterPresenter(view, get(), get(), get()) }
    factory { (view: ActionView) -> ActionPresenter(view, get(), get(), get()) }
    factory { (view: BudgetView) -> BudgetPresenter(view, get(), get(), get()) }
    factory { (view: ExpensesUIView) -> ExpensesUIPresenter(view, get(), get(), get()) }
    factory { (view: ExpensesView) -> ExpensesPresenter(view, get()) }
}