package com.example.android_resources.screens.converter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.android_resources.R
import kotlinx.android.synthetic.main.activity_converter.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.text.Typography.times


class ConverterView(private val activity: ConverterActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_converter, null)

    init {
        set_clicks()
    }

    fun set_clicks() {
        layout.converter_text_euro.onFocusChangeListener = OnFocusChangeListener { v, _ ->
            run {
                layout.converter_text_euro_layout.hint = ""
                layout.converter_layout.setOnClickListener {
                    sendToAct()
                }
            }
        }
        layout.converter_text_lei.onFocusChangeListener = OnFocusChangeListener { v, _ ->
            run {
                layout.converter_text_lei_layout.hint = ""
            }
        }
    }

    fun sendToAct() {
        if (!layout.converter_text_euro.text.isNullOrEmpty())
            activity.receiveFromView("EUR", "RON", layout.converter_text_euro.text.toString())
        else
            Log.d("text", "euro block is empty")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getRate(rate: String) {
        val euro = layout.converter_text_euro.text.toString()
        Log.d("Response", "euro is $euro")
        val euroD = euro.toDouble()
        Log.d("Response", "rate is $rate")
        val rateD = rate.toDouble()
        val result = euroD * rateD
        layout.converter_text_lei.setText(result.toString())
        layout.converter_text_lei_layout.hint = ""
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        layout.converter_title.text = "Converted using an API at $formatted"
    }
}