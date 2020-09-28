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

    fun setTitles() {
        val actions = getActions()
        var today = 0.0
        var week = 0.0
        var month = 0.0
        for (action in actions) {
            val date = DateUtils.convertDate(action.date)
            val now = LocalDateTime.now()

            today += actionsToday(date, now, action)
            week += actionsLastWeek(date, now, action)
            month += actionsLastMonth(date, now, action)
        }
        budgetView.updateCards(today, week, month)
    }

    private fun actionsToday(date: LocalDateTime, now: LocalDateTime, action: Action): Double {
        var today = 0.0
        //actions that happen today
        if (date.year == now.year) {
            if (date.month == now.month) {
                if (date.dayOfMonth == now.dayOfMonth) {
                    today += action.amount
                }
            }
        }
        return today
    }

    private fun actionsLastWeek(date: LocalDateTime, now: LocalDateTime, action: Action): Double {
        var week = 0.0
        //actions that happened in the last week
        if (date.year == now.year) {
            if (date.month == now.month) {
                if (date.dayOfMonth <= now.dayOfMonth) {
                    if (date.dayOfMonth + 7 >= now.dayOfMonth)
                        week += action.amount
                } else {
                    if (date.dayOfMonth + 7 <= now.dayOfMonth)
                        week += action.amount
                }
            }
        }
        return week
    }

    private fun actionsLastMonth(date: LocalDateTime, now: LocalDateTime, action: Action): Double {
        var month = 0.0
        //actions that happened in the last month
        if (date.year == now.year) {
            if (date.plusMonths(1).month == now.month && date.dayOfMonth >= now.dayOfMonth ||
                date.month == now.month && date.dayOfMonth <= now.dayOfMonth
            ) {
                month += action.amount
            }
        }
        return month
    }

    fun setChart(v: View, resources: Resources) {
        val data = BarData(getActions())
        var chartData = data.createChartData(resources)
        data.configureChartAppearance(v)
        data.prepareChartData(v, chartData)
    }
}