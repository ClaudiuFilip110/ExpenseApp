package com.example.android_resources.screens.expenses

import android.util.Log
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.utils.DateUtils
import org.threeten.bp.LocalDateTime

class ExpensesPresenter(val expensesView: ExpensesView, val userRepository: UserRepository) {
    fun getActions(): ArrayList<Action> {
        return userRepository.getActions()
    }

    //TODO: split
    fun dates() {
        val actions = getActions()
        var week = 0.0
        var month = 0.0
        var year = 0.0
        for (action in actions) {
            val date = DateUtils.convertDate(action.date)
            val now = LocalDateTime.now()
//            Log.d("ACTION", "--------------------------")
            //actions that happened in the last week
            if (date.year == now.year) {
                if (date.month == now.month) {
//                    Log.d("date", "date.plusDays(7) " + date.plusDays(7).dayOfMonth)
//                    Log.d("date", "now.dayOfMonth" + now.dayOfMonth)
                    if (date.dayOfMonth <= now.dayOfMonth) {
                        if (date.dayOfMonth + 7 >= now.dayOfMonth)
//                            Log.d("ACTION - THIS WEEK", date.toString())
                            week += action.amount
                    } else {
                        if (date.dayOfMonth + 7 <= now.dayOfMonth)
//                            Log.d("ACTION - THIS WEEK", date.toString())
                            week += action.amount
                    }
                }
            }

            //actions that happened in the last month
            if (date.year == now.year) {
                if (date.plusMonths(1).month == now.month && date.dayOfMonth >= now.dayOfMonth ||
                    date.month == now.month && date.dayOfMonth <= now.dayOfMonth
                ) {
//                    Log.d("ACTION - THIS MONTH", date.toString())
                    month += action.amount
                }
            }

            //actions that happen last year
            if (date.plusYears(1).year == now.year) {
                if (date.month > now.month) {
//                    Log.d("ACTION - THIS YEAR", date.toString())
                    year += action.amount
                } else if (date.month == now.month) {
                    if (date.dayOfMonth >= now.dayOfMonth) {
//                        Log.d("ACTION - THIS YEAR", date.toString())
                        year += action.amount
                    }
                }
            } else if (date.year == now.year) {
                if (date.month < now.month) {
//                    Log.d("ACTION - THIS YEAR", date.toString())
                    year += action.amount
                } else {
                    if (date.month == now.month) {
                        if (date.dayOfMonth <= now.dayOfMonth) {
//                            Log.d("ACTION - THIS YEAR", date.toString())
                            year += action.amount
                        }
                    }
                }
            }
        }
        ExpensesView.week = week
        ExpensesView.month = month
        ExpensesView.year = year
    }
}