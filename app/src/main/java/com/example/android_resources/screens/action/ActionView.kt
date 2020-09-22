package com.example.android_resources.screens.action

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.android_resources.R
import kotlinx.android.synthetic.main.activity_action.view.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.view.*
import java.net.URI

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
            Toast.makeText(activity.baseContext, "SAVE", Toast.LENGTH_SHORT).show()
            //activity.passObject()
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
}