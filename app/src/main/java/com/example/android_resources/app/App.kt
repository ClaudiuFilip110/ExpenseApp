package com.example.android_resources.app

import android.app.Application
import com.example.android_resources.app.dependecies.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    @Suppress("SpellCheckingInspection")
    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    dataModules,
                    preferencesModule,
                    presentersModules,
                    viewsModule,
                    rxModules
                )
            )
        }
    }
}