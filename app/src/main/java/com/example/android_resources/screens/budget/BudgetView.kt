package com.example.android_resources.screens.budget

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.android_resources.R
import kotlinx.android.synthetic.main.fragment_budget.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class BudgetView(private val fragment: BudgetFragment){
    val layout: View = View.inflate(fragment.context, R.layout.fragment_budget, null)

    fun setClickListeners(v: View) {
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

    fun getBalanceUntilDate(date: Date) {
        layout.budget_cur_bal_nr.text = fragment.getBalanceUntilDate(date).toString()
    }

    fun updateCards(today: Double, week: Double, month: Double) {
        layout.budget_today_exp_nr.text = today.toString()
        layout.budget_week_exp_nr.text = week.toString()
        layout.budget_month_exp_nr.text = month.toString()
    }
}