package com.example.android_resources.data.api.api

import com.example.android_resources.data.api.models.Info
import com.example.android_resources.data.api.models.Request
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("convert")
    fun convert(
        @Query("access_key") access_key: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String
    ): Call<Request>
}