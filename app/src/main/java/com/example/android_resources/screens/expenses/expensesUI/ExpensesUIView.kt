package com.example.android_resources.screens.expenses.expensesUI

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.screens.expenses.adapters.ExpensesAdapter
import com.example.android_resources.screens.expenses.expensesUI.ExpensesUIFragment
import kotlinx.android.synthetic.main.fragment_expenses_adapter.view.*

class ExpensesUIView(private val fragment: ExpensesUIFragment) {
    val layout: View = View.inflate(fragment.context, R.layout.fragment_expenses_adapter, null)

    fun setTitle(title: Any?) {
        if (title == null)
            return
        layout.expenses_total_nr.text = title.toString()
    }

    fun setAdapter(title: Any?, list: ArrayList<Action>, balance: ArrayList<Double>, resources: Resources) {
        if (title == null)
            return
        val recycler = layout.expenses_recycler_view
        recycler.layoutManager = LinearLayoutManager(fragment.context)
        recycler.adapter = fragment.context?.let { ExpensesAdapter(it, list, balance, resources) }
    }
}