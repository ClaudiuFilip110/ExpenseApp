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
    var category = String()

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
            Toast.makeText(activity.baseContext, "SAVE", Toast.LENGTH_SHORT).show()
//            activity.passObject()
//            if (validateDate()) {
//                Toast.makeText(activity.baseContext, "Date validated", Toast.LENGTH_SHORT)
//                    .show()
//                if (validateAmount()) {
//                    Toast.makeText(activity.baseContext, "Amount validated", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
            validateCategory()


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
            Toast.makeText(activity.baseContext, "amount can't be null", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateDate(): Boolean {
        if (layout.action_date_text.text.isNullOrEmpty()) {
            Toast.makeText(activity.baseContext, "date can't be null", Toast.LENGTH_SHORT).show()
            return false
        }
        val sDate1 = layout.action_date_text.text.toString()
        try {
            var date1: Date = SimpleDateFormat("dd-MM-yyyy HH:mm").parse(sDate1)
            Log.d("date", "$sDate1 before + \"\\t\" + $date1 after")
            return true
        } catch (e: Exception) {
            try {
                var date2: Date = SimpleDateFormat("dd-MM-yyyy").parse(sDate1)
                Log.d("date", "$sDate1 before + \"\\t\" + $date2 after")
                return true
            } catch (e: Exception) {
                Toast.makeText(activity.baseContext, "date format incorrect", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return false
    }

    private fun addCategory() {
//        layout.action_recycler_view.setOnClickListener {
//            Toast.makeText(activity.baseContext, "recycler clicked", Toast.LENGTH_SHORT).show()
//            category = layout.action_recycler_view.action_rec_text.text.toString()
//        }
    }


    private fun validateCategory() {
        if (category.isEmpty()) {
            Log.d("category", "category is null")
        }
        Log.d("category", "category is $category")
    }

    init {
//        addCategory()
    }
}