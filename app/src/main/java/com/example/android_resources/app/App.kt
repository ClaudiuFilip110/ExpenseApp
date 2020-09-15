package com.example.android_resources.app

import android.app.Application
import com.example.android_resources.app.dependecies.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
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