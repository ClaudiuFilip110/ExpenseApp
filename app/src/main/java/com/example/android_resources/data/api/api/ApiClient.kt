package com.example.android_resources.data.api.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var BASE_URL = "https://romanian-exchange-rate-bnr-api.herokuapp.com/api/"

    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val converterAPI: ConverterAPI
        get() = retrofit
            .create<ConverterAPI>(
                ConverterAPI::class.java
            )
}