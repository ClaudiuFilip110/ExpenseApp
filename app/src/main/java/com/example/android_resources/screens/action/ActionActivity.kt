package com.example.android_resources.screens.action

import android.app.Activity
import android.content.Intent
import android.drm.DrmStore
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.data.database.entities.Action
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf

class ActionActivity : AppCompatActivity(), KoinComponent {
    private val view: ActionView by inject { parametersOf(this) }
    private val presenter: ActionPresenter by inject { parametersOf(view, get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.layout)
        view.getRecycler()
    }

    fun getRecycler(recycler: RecyclerView) {
        presenter.getRecycler(recycler, this)
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

    fun viewActions(){
        presenter.viewActions()
    }

    fun deleteActions(){
        presenter.deleteActions()
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ActionActivity::class.java)
            activity.startActivity(intent)
        }

        //image pick code
        private val IMAGE_PICK_CODE = 1000;

        //Permission code
        private val PERMISSION_CODE = 1001;
    }
}