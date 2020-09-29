package com.example.android_resources.screens.action

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.screens.main.MainActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf
import java.io.File
import java.io.FileOutputStream

class ActionActivity : AppCompatActivity(), KoinComponent {
    private val view: ActionView by inject { parametersOf(this) }
    private val presenter: ActionPresenter by inject { parametersOf(view, get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        view.getRecycler()
        isObjectPassed()
    }

    private fun isObjectPassed() {
        if (intent.getSerializableExtra("object") == null) return
        val action = intent.getSerializableExtra("object")
        view.populateWithActivity(action)
    }

    fun passRecyclerData(): ArrayList<Action> {
        return presenter.passRecyclerData()
    }

    fun goBack() {
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            view.setImage(data?.data)
        }
    }

    fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        ActivityCompat.startActivityForResult(this, intent, ActionActivity.IMAGE_PICK_CODE, null)
    }

    fun addToDB(action: Action) {
        presenter.addToDB(action)
    }

    fun finishAct() {
        val i = Intent(this, MainActivity::class.java)
        startActivityForResult(i, 1)
        finish()
    }

    fun getLastId(): Int {
        return presenter.getLastId()
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, ActionActivity::class.java)
            activity.startActivity(intent)
        }

        //image pick code
        private val IMAGE_PICK_CODE = 1000;

        //Permission code
        private val PERMISSION_CODE = 1001;
    }
}