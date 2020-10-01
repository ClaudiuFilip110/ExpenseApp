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
import kotlinx.android.synthetic.main.toolbar_back_arrow.view.*
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.text.Typography.times


class ConverterView(private val activity: ConverterActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_converter, null)

    init {
        initConverterFocusListener()
        initBackListener()
    }

    private fun initBackListener() {
        layout.toolbar_back_image.setOnClickListener {
            activity.goBack()
        }
    }

    private fun initConverterFocusListener() {
        layout.converter_text_euro.onFocusChangeListener = OnFocusChangeListener { _, _ ->
            run {
                layout.converter_text_euro_layout.hint = ""
                layout.converter_layout.setOnClickListener {
                    sendToAct()
                }
            }
        }
        layout.converter_text_lei.onFocusChangeListener = OnFocusChangeListener { _, _ ->
            run {
                layout.converter_text_lei_layout.hint = ""
            }
        }
    }

    private fun sendToAct() {
        if (!layout.converter_text_euro.text.isNullOrEmpty())
            activity.receiveFromView()
        else
            Timber.d("euro block is empty")
    }


    fun setLeiAmount(rate: String) {
        val euro = layout.converter_text_euro.text.toString()
        val euroD = euro.toDouble()
        val rateD = rate.toDouble()
        val result = euroD * rateD
        layout.converter_text_lei.setText(result.toString())
        layout.converter_text_lei_layout.hint = ""
        val formattedTime = activity.receiveFormattedText()
        layout.converter_title.text = "Converted using an API at $formattedTime"
    }
}