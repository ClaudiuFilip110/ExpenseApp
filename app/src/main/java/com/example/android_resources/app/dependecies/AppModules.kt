package com.example.android_resources.app.dependecies

import com.example.android_resources.utils.rxUtils.AppRxSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.module.Module
import org.koin.dsl.module

val rxModules: Module = module {
    single { AppRxSchedulers() }
    factory { CompositeDisposable() }
}