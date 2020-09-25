package com.example.android_resources.screens.converter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.android_resources.data.api.api.ApiClient
import com.example.android_resources.data.api.models.Request
import com.example.android_resources.data.database.repositories.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConverterPresenter(
    private val converterView: ConverterView,
    private val userRepository: UserRepository
) {
    val API_KEY = "f7dbe1842278-43779b"
    fun onCreate() {
    }

    fun receiveFromAct(from: String, to: String, amount: String) {
        var call: Call<Request> = ApiClient.converterAPI.convert(API_KEY, from, to, amount)
        call.enqueue(object : Callback<Request> {
            override fun onFailure(call: Call<Request>, t: Throwable) {
                Log.d("Response", "response failed " + t.toString())
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<Request>, response: Response<Request>) {
                if (response.isSuccessful) {
                    val request = response.body() ?: return
                    val rate = request.info.rate
                    Log.d("Response", "response code " + response.code().toString())
                    converterView.getRate(rate)
                } else
                    Log.d("Response", "response did not fail but is null")
            }
        })
    }
}