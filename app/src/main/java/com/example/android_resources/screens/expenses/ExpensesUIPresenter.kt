package com.example.android_resources.screens.expenses

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.budget.BudgetView
import com.example.android_resources.screens.expenses.adapters.ExpensesAdapter
import kotlinx.android.synthetic.main.fragment_expenses_adapter.view.*

class ExpensesUIPresenter(val expensesUIView: ExpensesUIView, val userRepository: UserRepository) {

    fun recycler(v: View, context: Context) {
        val recycler = v.expenses_recycler_view
        val list = getActionsFromDB()
        recycler.layoutManager = LinearLayoutManager(v.context)
        recycler.adapter = ExpensesAdapter(context, list)
    }

    private fun getActionsFromDB(): ArrayList<Action> {
        return userRepository.getActions()
    }
}