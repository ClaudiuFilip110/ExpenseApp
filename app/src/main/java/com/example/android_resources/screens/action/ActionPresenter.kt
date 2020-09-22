package com.example.android_resources.screens.action

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.action.adapters.ActionsAdapter

class ActionPresenter(
    private val actionView: ActionView,
    private val userRepository: UserRepository
) {
    fun getRecycler(recycler: RecyclerView, actionActivity: ActionActivity) {
        recycler.layoutManager =
            GridLayoutManager(actionActivity, 2, GridLayoutManager.HORIZONTAL, false)
        val list = ArrayList<String>()
        list.add("Claudiu")
        list.add("Filip")
        list.add("de la vrancea")
        list.add("de la vrancea")
        list.add("de la vrancea")
        list.add("Claudiu")
        list.add("Filip")
        list.add("Claudiu")
        list.add("Filip")
        list.add("Claudiu")
        list.add("Filip")

        recycler.adapter = ActionsAdapter(list)
    }
}