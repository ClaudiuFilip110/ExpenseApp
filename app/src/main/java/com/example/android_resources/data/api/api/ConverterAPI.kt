package com.example.android_resources.data.api.api

import com.example.android_resources.data.api.models.Request
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface ConverterAPI {
    @GET("rate/EUR")
    fun convert(): Observable<Request>
}