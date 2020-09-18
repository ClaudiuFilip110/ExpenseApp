package com.example.android_resources.screens.converter

import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import com.example.android_resources.R
import kotlinx.android.synthetic.main.activity_converter.view.*


class ConverterView(private val activity: ConverterActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_converter, null)

    init {
        set_clicks()
    }

    fun set_clicks() {
        layout.converter_text_euro.onFocusChangeListener = OnFocusChangeListener { v, _ ->
            run {
                layout.converter_text_euro_layout.hint = ""
            }
        }
        layout.converter_text_lei.onFocusChangeListener = OnFocusChangeListener { v, _ ->
            run {
                layout.converter_text_lei_layout.hint = ""
            }
        }
    }
}