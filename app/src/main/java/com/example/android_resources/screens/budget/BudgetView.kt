package com.example.android_resources.screens.budget

import android.view.View
import android.widget.Toast
import com.example.android_resources.R
import kotlinx.android.synthetic.main.fragment_budget.view.*

class BudgetView(private val fragment: BudgetFragment){
    val layout: View = View.inflate(fragment.context, R.layout.fragment_budget, null)

    fun clickOnCard(v: View) {
        v.budget_cur_bal_card.setOnClickListener {
            Toast.makeText(fragment.context, "TEXT", Toast.LENGTH_SHORT).show()
        }
        v.budget_today_exp_card.setOnClickListener {
            Toast.makeText(fragment.context, "TEXT", Toast.LENGTH_SHORT).show()
        }
        v.budget_week_exp_card.setOnClickListener {
            Toast.makeText(fragment.context, "TEXT", Toast.LENGTH_SHORT).show()
        }
        v.budget_month_exp_card.setOnClickListener {
            Toast.makeText(fragment.context, "TEXT", Toast.LENGTH_SHORT).show()
        }
    }
}