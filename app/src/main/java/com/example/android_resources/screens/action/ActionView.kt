package com.example.android_resources.screens.action

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.screens.action.adapters.ActionsAdapter
import com.example.android_resources.utils.DateUtils
import kotlinx.android.synthetic.main.activity_action.view.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.view.*
import org.threeten.bp.LocalDateTime
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ActionView(private val activity: ActionActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_action, null)
    var calendarDate = ""
    var timePicker = ""

    init {
        layout.toolbar_save.visibility = TextView.VISIBLE
        layout.toolbar_text.text = "Add Action"
        layout.action_delete_pdf.visibility = TextView.INVISIBLE
        initSaveListener()
        initAddImageListener()
        initCalendar()
        initTimePicker()
    }

    fun setRecycler() {
        val recycler = layout.action_recycler_view
        val list = activity.passRecyclerData()
        recycler.layoutManager =
            GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
        recycler.adapter = ActionsAdapter(activity.baseContext, list)
    }

    private fun initBackListener() {
        layout.toolbar_back_image.setOnClickListener {
            activity.goBack()
        }
    }

    private fun initAddImageListener() {
        layout.action_add_image.setOnClickListener {
            activity.pickImage()
        }
    }

    private fun initCalendar() {
        val calendar = Calendar.getInstance()
        layout.action_calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val date = DateUtils.convertSimpleDate(calendar.time)
            calendarDate = date.toString()
        }
    }

    private fun initTimePicker() {
        val time = layout.action_time
        time.setOnTimeChangedListener { v, hour, minute ->
            timePicker = "$hour:$minute"
        }
    }

    private fun initSaveListener() {
        layout.toolbar_save.setOnClickListener {
            activity.initLastId()
        }
    }

    fun saveWithLastId(lastId: Int) {
        Timber.d("lastId is %s", lastId)
        if (selected) {
            layout.action_delete_pdf.visibility = TextView.VISIBLE
            updateAction(selectedAction)
            activity.finishAct()
        } else
            if (validateData()) {
                layout.action_delete_pdf.visibility = TextView.VISIBLE
                val action = Action()
                val date1 = convertFromStringToDate(calendarDate, timePicker)
                action.date = date1
                action.category = category
                if (category == "Income")
                    action.amount = layout.action_amount_text.text.toString().toDouble()
                else
                    action.amount = -layout.action_amount_text.text.toString().toDouble()
                action.details = layout.action_details_text.text.toString()
                if (layout.action_details_pdf.drawable != null)
                    action.detailsImage = lastId.toString() + "_pdf"
                storeImageInFiles(lastId)
                activity.addToDB(action)
                activity.finishAct()
            }
    }

    private fun updateAction(action: Action) {
        if (calendarDate != "" && timePicker != "")
            action.date = convertFromStringToDate(calendarDate, timePicker)
        if (category != "")
            action.category = category
        action.amount = layout.action_amount_text.text.toString().toDouble()
        action.details = layout.action_details_text.text.toString()
        action.detailsImage
        if (layout.action_details_pdf.drawable != null)
            action.detailsImage = action.id.toString() + "_pdf"
        storeImageInFiles(action.id.toInt())

        activity.sendUpdatedAction(action)
    }

    private fun validateData(): Boolean {
        if (validateAmount() && validateCategory()) {
            return true
        }
        return false
    }

    private fun storeImageInFiles(id: Int) {
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
        }
    }

    private fun deletePhoto() {
        layout.action_delete_pdf.setOnClickListener {
            if (layout.action_details_pdf.drawable != null)
                layout.action_details_pdf.setImageDrawable(null);
        }
    }

    fun setImage(data: Uri?) {
        layout.action_details_pdf.setImageURI(data)
    }

    init {
        initBackListener()
        deletePhoto()
    }

    private fun validateAmount(): Boolean {
        if (layout.action_amount_text.text.isNullOrEmpty()) {
            Toast.makeText(activity.baseContext, "amount can't be null", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun convertFromStringToDate(date: String, time: String): Date {
        try {
            return SimpleDateFormat("yyyy-MM-dd HH:mm").parse("$date $time")
        } catch (e: Exception) {
            try {
                return SimpleDateFormat("yyyy-MM-dd").parse(date)
            } catch (e: Exception) {
            }
        }
        return Date()
    }

    private fun validateCategory(): Boolean {
        if (category.isEmpty()) {
            Toast.makeText(
                activity.baseContext,
                "choose a category",
                Toast.LENGTH_SHORT
            )
                .show()
            return false
        }
        return true
    }

    fun populateWithActionData(action: Action?) {
        if (action == null)
            return
        val localdatetime = DateUtils.convertDate(action.date)
        val calendar = Calendar.getInstance()
        calendar.set(
            localdatetime.year,
            localdatetime.minusMonths(1).monthValue,
            localdatetime.dayOfMonth
        )
        val long = calendar.timeInMillis

        //initiate recycler
        initSelectedRecycler(action)

        //update layout
        initSelectedUpdateLayout(action, long, localdatetime)

        //update VARIABLES
        selected = true
        selectedAction = action
    }

    private fun initSelectedUpdateLayout(action: Action, long: Long, localdatetime: LocalDateTime) {
        layout.action_calendar.date = long
        layout.action_amount_text.setText(action.amount.toString())
        layout.action_details_text.setText(action.details)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layout.action_time.hour = localdatetime.hour
            layout.action_time.minute = localdatetime.minute
        }
        if (action.detailsImage.isNotEmpty()) {
            val path = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                File(activity.dataDir, "files" + File.separator + "Images")
            } else {
                File("")
            }
            val imageView = layout.action_details_pdf
            val outFile = File(path, action.id.toString() + "_pdf" + ".jpeg")
            val myBitmap = BitmapFactory.decodeFile(outFile.absolutePath)
            imageView.setImageBitmap(myBitmap)
        }
    }

    private fun initSelectedRecycler(action: Action) {
        val recycler = layout.action_recycler_view
        val list = activity.passRecyclerData()
        recycler.layoutManager =
            GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
        recycler.adapter = ActionsAdapter(activity.baseContext, list, true, action.category)
    }

    companion object {
        var category = ""
        var selected = false
        var selectedAction = Action()
    }
}