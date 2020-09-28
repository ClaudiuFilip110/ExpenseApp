package com.example.android_resources.screens.action

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import kotlinx.android.synthetic.main.activity_action.view.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.view.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


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
        //TODO: here the adapter should be set the adapter of the recycler with the data retrieved from the presenter
        activity.getRecycler(recycler)
    }

    //TODO: goBack should be renamed to something like: initializeBackClickListener()
    fun goBack() {
        layout.toolbar_back_image.setOnClickListener {
            activity.goBack()
        }
    }

    //TODO: addImage should be renamed to something like: initializeAddImageClickListener()
    fun addImage() {
        layout.action_add_image.setOnClickListener {
            activity.pickImage()
        }
    }

    //TODO: same here
    fun save() {
        layout.toolbar_save.setOnClickListener {
//            activity.deleteActions()
//            activity.viewActions()
            val lastId = activity.getLastId() + 1
            Toast.makeText(activity.baseContext, "SAVE", Toast.LENGTH_SHORT).show()
            //TODO: you can create a function that verify all the validations
            if (validateDate()) {
                if (validateAmount()) {
                    if (validateCategory()) {
                        //add object to db
                        val action = Action()
                        val date1 = tryConversion(layout.action_date_text.text.toString())
                        if (date1 != null) {
                            action.date = date1
                            action.category = category
                            if (category == "Income")
                                action.amount = layout.action_amount_text.text.toString().toDouble()
                            else
                                action.amount =
                                    -layout.action_amount_text.text.toString().toDouble()
                            action.details = layout.action_details_text.text.toString()
                            if (layout.action_details_pdf.drawable != null)
                                action.detailsImage = lastId.toString() + "_pdf"
                            storeImageInFiles(lastId)
                            activity.addToDB(action)
                            activity.finishAct()
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

    fun storeImageInFiles(id: Int) {
        val imageView = layout.action_details_pdf
        val path = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File(activity.dataDir, "files" + File.separator + "Images")
        } else {
            File("")
        }
        if (!path.exists()) {
            path.mkdirs()
        } else {
            val drawable: Drawable? = imageView.drawable ?: return
            val bitmap = (drawable as BitmapDrawable?)?.bitmap ?: return
            //write to file
            val outFile = File(path, id.toString() + "_pdf" + ".jpeg")
            val stream = FileOutputStream(outFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            //write to db
            val imageView = layout.action_details_pdf
            imageView.setImageURI(Uri.parse(outFile.toString()))
            //asta l-am lasta aici in caz ca am nevoie mai tarziu de conversia asta
//            bitmap = (drawable as BitmapDrawable).bitmap
//            val byteArrayOutputStream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
//            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
//            val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
//            Log.d("encoded", "image encoded $encoded")
//
//            Glide.with(activity)
//                .load(encoded)
//                .into(layout.action_details_pdf)
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

    //TODO: this can be rethink to add the message on the false return
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
            return date1
        } catch (e: Exception) {
            try {
                var date2: Date? = SimpleDateFormat("dd-MM-yyyy").parse(sDate1)
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
        return true
    }

    fun passContext(): Context {
        return activity.baseContext
    }

    companion object {
        var category = String()
    }
}