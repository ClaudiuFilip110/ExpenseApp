package com.example.android_resources.screens.expenses.expensesUI

import android.view.View
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.expenses.PieChart
import com.example.android_resources.utils.DateUtils
import org.threeten.bp.LocalDateTime
import kotlin.collections.ArrayList

class ExpensesUIPresenter(val expensesUIView: ExpensesUIView, val userRepository: UserRepository) {
    private fun getActionsFromDB(): ArrayList<Action> {
        return userRepository.getActions()
    }

    fun chart(v: View, title: Any?) {
        if (title == null)
            return
        val pieChart =
            PieChart(adapterList(title))
        pieChart.instantiate(v)
    }

    fun getBalanceUntilDate(title: Any?): ArrayList<Double> {
        if (title == null)
            return ArrayList()
        var list = adapterList(title)
        var balanceList = ArrayList<Double>()
        var amounts = ArrayList<Double>()
        for (action in list) {
            balanceList.add(userRepository.getBalanceUntilDate(action.date))
        }
        return balanceList
    }

    fun getListUntilDate(title: Any?): ArrayList<Action> {
        if (title == null)
            return ArrayList()
        return adapterList(title)
    }

    private fun adapterList(title: Any?): ArrayList<Action> {
        val finalArray = ArrayList<Action>()
        if (title == null)
            return ArrayList()
        val position = title.toString()
        val actions = getActionsFromDB()
        when (position) {
            "week" -> {
                weekCase(actions, finalArray)
            }
            "month" -> {
                monthCase(actions, finalArray)
            }
            "year" -> {
                yearCase(actions, finalArray)
            }
        }
        return finalArray
    }

    private fun yearCase(actions: ArrayList<Action>, finalArray: ArrayList<Action>) {
        for (action in actions) {
            val date = DateUtils.convertDate(action.date)
            val now = LocalDateTime.now()
            if (date.plusYears(1).year == now.year) {
                if (date.month > now.month) {
                    finalArray.add(action)
                } else if (date.month == now.month) {
                    if (date.dayOfMonth >= now.dayOfMonth) {
                        finalArray.add(action)
                    }
                }
            } else if (date.year == now.year) {
                if (date.month < now.month) {
                    finalArray.add(action)
                } else {
                    if (date.month == now.month) {
                        if (date.dayOfMonth <= now.dayOfMonth) {
                            finalArray.add(action)
                        }
                    }
                }
            }
        }
    }

    private fun monthCase(actions: ArrayList<Action>, finalArray: ArrayList<Action>) {
        for (action in actions) {
            val date = DateUtils.convertDate(action.date)
            val now = LocalDateTime.now()
            if (date.year == now.year) {
                if (date.plusMonths(1).month == now.month && date.dayOfMonth >= now.dayOfMonth ||
                    date.month == now.month && date.dayOfMonth <= now.dayOfMonth
                ) {
                    finalArray.add(action)
                }
            }
        }
    }

    private fun weekCase(actions: ArrayList<Action>, finalArray: ArrayList<Action>) {
        for (action in actions) {
            val date = DateUtils.convertDate(action.date)
            val now = LocalDateTime.now()
            if (date.year == now.year) {
                if (date.month == now.month) {
                    if (date.dayOfMonth <= now.dayOfMonth) {
                        if (date.dayOfMonth + 7 >= now.dayOfMonth)
                            finalArray.add(action)
                    } else {
                        if (date.dayOfMonth + 7 <= now.dayOfMonth)
                            finalArray.add(action)
                    }
                }
            }
        }
    }
}