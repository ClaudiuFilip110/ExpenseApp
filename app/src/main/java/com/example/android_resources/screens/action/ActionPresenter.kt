package com.example.android_resources.screens.action

import android.media.Image
import android.util.Base64
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.action.adapters.ActionsAdapter

class ActionPresenter(
    private val actionView: ActionView,
    private val userRepository: UserRepository
) {
    fun getRecycler(recycler: RecyclerView, actionActivity: ActionActivity) {
        recycler.layoutManager =
            GridLayoutManager(actionActivity, 2, GridLayoutManager.HORIZONTAL, false)
        val list = ArrayList<Action>()
        var action = Action()
        action.category = "Income"
        list.add(action)
        action = Action()
        action.category = "Food"
        list.add(action)
        action = Action()
        action.category = "Car"
        list.add(action)
        action = Action()
        action.category = "Clothes"
        list.add(action)
        action = Action()
        action.category = "Savings"
        list.add(action)
        action = Action()
        action.category = "Health"
        list.add(action)
        action = Action()
        action.category = "Beauty"
        list.add(action)
        action = Action()
        action.category = "Travel"
        list.add(action)
        recycler.adapter = ActionsAdapter(actionView.passContext(), list)
    }

    fun addToDB(action: Action) {
        userRepository.insertAction(action)
    }

    fun viewActions() {
        val actions: ArrayList<Action> = userRepository.getActions()
        if (actions.size == 0) {
            Log.d("ACTIONS", "no actions in the db")
        } else {
            Log.d("ACTIONS", "ACTIONS----------")
            for (action: Action in userRepository.getActions()) {
                Log.d("ACTIONS", action.toString())
            }
        }
    }

    fun deleteActions() {
        userRepository.deletAllActions()
    }

    fun getLastId(): Int {
        return userRepository.getLastId()
    }
}