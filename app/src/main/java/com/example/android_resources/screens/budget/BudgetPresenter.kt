package com.example.android_resources.screens.budget

import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.View
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.utils.DateUtils
import java.text.SimpleDateFormat
import org.threeten.bp.LocalDateTime;
import java.util.*

class BudgetPresenter(val budgetView: BudgetView, val userRepository: UserRepository) {
    fun getBalanceUntilDate(date: Date): Double {
        for (action in userRepository.getActions()) {
            Log.d("ACTION", action.toString())
        }
        return userRepository.getBalanceUntilDate(date)
    }

    fun getActions(): ArrayList<Action> {
        return userRepository.getActions()
    }

    fun dates() {
        val actions = getActions()
        var today = 0.0
        var week = 0.0
        var month = 0.0
        for (action in actions) {
            val date = DateUtils.convertDate(action.date)
            val now = LocalDateTime.now()

            //actions that happen today
            if (date.year == now.year) {
                if (date.month == now.month) {
                    if (date.dayOfMonth == now.dayOfMonth) {
//                        Log.d("ACTION - TODAY", date.toString())
                        today += action.amount
                    }
                }
            }

            //actions that happened in the last week
            if (date.year == now.year) {
                if (date.month == now.month) {
//                    Log.d("date", "date.plusDays(7) " + date.plusDays(7).dayOfMonth)
//                    Log.d("date", "now.dayOfMonth" + now.dayOfMonth)
                    if (date.dayOfMonth <= now.dayOfMonth) {
                        if (date.dayOfMonth + 7 >= now.dayOfMonth)
//                        Log.d("ACTION - THIS WEEK", date.toString())
                            week += action.amount
                    } else {
                        if (date.dayOfMonth + 7 <= now.dayOfMonth)
//                        Log.d("ACTION - THIS WEEK", date.toString())
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
        }
        budgetView.updateCards(today, week, month)
    }

    fun chart(v: View, resources: Resources) {
        val data = BarData(getActions())
        var chartData = data.createChartData(resources)
        data.configureChartAppearance(v)
        data.prepareChartData(v, chartData)
    }
}