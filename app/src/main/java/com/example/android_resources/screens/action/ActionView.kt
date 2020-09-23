package com.example.android_resources.screens.action

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.view.iterator
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.screens.action.adapters.ActionsAdapter
import kotlinx.android.synthetic.main.activity_action.view.*
import kotlinx.android.synthetic.main.activity_converter.view.*
import kotlinx.android.synthetic.main.recyclerview_action.view.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.view.*
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ActionView(private val activity: ActionActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_action, null)

    init {
        layout.toolbar_save.visibility = TextView.VISIBLE
        layout.toolbar_text.text = "Add Action"
        save()
        addImage()
    }

    fun getRecycler() {
        val recycler = layout.action_recycler_view
        activity.getRecycler(recycler)
    }

    fun goBack() {
        layout.toolbar_back_image.setOnClickListener {
            activity.goBack()
        }
    }

    fun addImage() {
        layout.action_add_image.setOnClickListener {
            activity.pickImage()
        }
    }

    fun save() {
        layout.toolbar_save.setOnClickListener {
//            activity.deleteActions()
            activity.viewActions()
//            Toast.makeText(activity.baseContext, "SAVE", Toast.LENGTH_SHORT).show()
            if (validateDate()) {
                Toast.makeText(activity.baseContext, "Date validated", Toast.LENGTH_SHORT)
                    .show()
                if (validateAmount()) {
                    Toast.makeText(activity.baseContext, "Amount validated", Toast.LENGTH_SHORT)
                        .show()
                    if (validateCategory()) {
                        Toast.makeText(
                            activity.baseContext,
                            "Category validated",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        //add object to db
                        var action = Action()
                        var date1 = tryConversion(layout.action_date_text.text.toString())
                        if (date1 != null) {
                            action.date = date1
                            action.amount = layout.action_amount_text.text.toString().toDouble()
                            action.categoryImage = image
                            action.category = category
                            action.details = layout.action_details_text.text.toString()
                            activity.addToDB(action)
                        }
                    } else {
                        Toast.makeText(
                            activity.baseContext,
                            "choose a category",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    Toast.makeText(activity.baseContext, "amount can't be null", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(activity.baseContext, "date can't be null", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }

    fun deletePhoto() {
        layout.action_delete_pdf.setOnClickListener {
            if (layout.action_details_pdf.drawable != null)
                layout.action_details_pdf.setImageDrawable(null);
        }
    }

    fun setImage(data: Uri?) {
        layout.action_details_pdf.setImageURI(data)
    }

    init {
        goBack()
        deletePhoto()
    }

    private fun validateAmount(): Boolean {
        if (layout.action_amount_text.text.isNullOrEmpty()) {
            return false
        }
        return true
    }

    private fun validateDate(): Boolean {
        if (layout.action_date_text.text.isNullOrEmpty()) {
            return false
        }
        val sDate1 = layout.action_date_text.text.toString()
        val sDateConv = tryConversion(sDate1)
        if (sDateConv != null)
            return true
        return false
    }

    private fun validateDetails(): Boolean {
        if (layout.action_details_text.text.isNullOrEmpty()) {
            return false
        }
        return true
    }

    fun tryConversion(sDate1: String): Date? {
        try {
            var date1: Date? = SimpleDateFormat("dd-MM-yyyy HH:mm").parse(sDate1)
//            Log.d("date", "$sDate1 before + \"\\t\" + $date1 after")
            return date1
        } catch (e: Exception) {
            try {
                var date2: Date? = SimpleDateFormat("dd-MM-yyyy").parse(sDate1)
//                Log.d("date", "$sDate1 before + \"\\t\" + $date2 after")
                return date2
            } catch (e: Exception) {
                Toast.makeText(activity.baseContext, "date format incorrect", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return null
    }

    private fun validateCategory(): Boolean {
        if (category.isNullOrEmpty()) {
            Log.d("category", "category is null")
            return false
        }
        Log.d("category", "category is $category")
        return true
    }

    companion object {
        var category = String()
        var image = String()
    }
}