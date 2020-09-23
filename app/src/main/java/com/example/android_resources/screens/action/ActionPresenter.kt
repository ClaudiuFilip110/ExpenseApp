package com.example.android_resources.screens.action

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
        action.category = "Food"
        list.add(action)
        action.category = "Car"
        list.add(action)
        action.category = "Clothes"
        list.add(action)
        action.category = "Savings"
        list.add(action)
        action.category = "Health"
        list.add(action)
        action.category = "Beauty"
        list.add(action)
        action.category = "Travel"
        list.add(action)

        recycler.adapter = ActionsAdapter(list)

    }
}