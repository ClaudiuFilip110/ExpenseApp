package com.example.android_resources.app.dependecies

import com.example.android_resources.data.database.RoomDB
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.data.preferences.Preferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module = module {
    single { RoomDB(androidContext()).appDatabase }
}

val preferencesModule: Module = module {
    single { Preferences(androidContext()) }
}

val dataModules: Module = module {
    single { UserRepository(get()) }
}