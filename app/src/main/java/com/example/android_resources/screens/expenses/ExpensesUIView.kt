package com.example.android_resources.screens.expenses

import android.view.View
import com.example.android_resources.R
import com.example.android_resources.screens.budget.BudgetFragment
import kotlinx.android.synthetic.main.fragment_expenses_adapter.view.*

class ExpensesUIView(private val fragment: ExpensesUIFragment) {
    val layout: View = View.inflate(fragment.context, R.layout.fragment_expenses_adapter, null)

    fun setTitle(title: Any?) {
        if (title == null)
            return
        layout.expenses_total_nr.text = title.toString()
    }
}