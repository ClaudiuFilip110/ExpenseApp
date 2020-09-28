package com.example.android_resources.screens.expenses

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.expenses.adapters.ExpensesAdapter
import com.example.android_resources.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_expenses_adapter.view.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ExpensesUIPresenter(val expensesUIView: ExpensesUIView, val userRepository: UserRepository) {
    private fun getActionsFromDB(): ArrayList<Action> {
        return userRepository.getActions()
    }

    fun chart(v: View, title: Any?) {
        if (title == null)
            return
        val pieChart = PieChart(dates(title))
        pieChart.instantiate(v)
    }

    //TODO: move recycler to the view class
    fun setAdapter(title: Any?, v: View, context: Context) {
        if (title == null)
            return
        var list = dates(title)
        var amounts = ArrayList<Double>()
        for (action in list) {
            amounts.add(userRepository.getBalanceUntilDate(action.date))
        }
        val recycler = v.expenses_recycler_view
        recycler.layoutManager = LinearLayoutManager(v.context)
        recycler.adapter = ExpensesAdapter(context, list, amounts)
    }

    //TODO: split
    fun dates(title: Any?): ArrayList<Action> {
        val finalArray = ArrayList<Action>()
        if (title == null)
            return ArrayList()
        val position = title.toString()
        val actions = getActionsFromDB()
        when (position) {
            "week" -> {
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
            "month" -> {
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
            "year" -> {
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
        }
        return finalArray
    }
}