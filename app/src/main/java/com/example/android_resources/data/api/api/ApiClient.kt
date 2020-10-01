package com.example.android_resources.data.api.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var BASE_URL = "http://currency.joover.com/"

    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val converterAPI: ConverterAPI
        get() = retrofit
            .create<ConverterAPI>(
                ConverterAPI::class.java
            )
}