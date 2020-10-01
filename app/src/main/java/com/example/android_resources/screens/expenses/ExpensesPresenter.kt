package com.example.android_resources.screens.expenses

import android.util.Log
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.utils.DateUtils
import org.threeten.bp.LocalDateTime
import timber.log.Timber

class ExpensesPresenter(val expensesView: ExpensesView, val userRepository: UserRepository) {
    fun getActions(): ArrayList<Action> {
        return userRepository.getActions()
    }

    fun dates() {
        val actions = getActions()
        var week = 0.0
        var month = 0.0
        var year = 0.0
        for (action in actions) {
            val date = DateUtils.convertDate(action.date)
            val now = LocalDateTime.now()
            week += weekCase(action, date, now)
            month += monthCase(action, date, now)
            year += yearCase(action, date, now)
        }
        ExpensesView.week = week
        ExpensesView.month = month
        ExpensesView.year = year
    }


    private fun weekCase(action: Action, date: LocalDateTime, now: LocalDateTime): Double {
        //actions that happened in the last week
        var week = 0.0
        if (date.year == now.year) {
            if (date.month == now.month) {
                if (date.dayOfMonth <= now.dayOfMonth) {
                    if (date.dayOfMonth + 7 > now.dayOfMonth)
                        week += action.amount
                }
            } else if (date.plusMonths(1).month == now.month) {
                if (date.dayOfMonth> now.minusDays(7).dayOfMonth) {
                    week += action.amount
                }
            }
        }
        return week
    }

    private fun monthCase(action: Action, date: LocalDateTime, now: LocalDateTime): Double {
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

    private fun yearCase(action: Action, date: LocalDateTime, now: LocalDateTime): Double {
        var year = 0.0
        //actions that happen last year
        if (date.plusYears(1).year == now.year) {
            if (date.month > now.month) {
                year += action.amount
            } else if (date.month == now.month) {
                if (date.dayOfMonth >= now.dayOfMonth) {
                    year += action.amount
                }
            }
        } else if (date.year == now.year) {
            if (date.month < now.month) {
                year += action.amount
            } else {
                if (date.month == now.month) {
                    if (date.dayOfMonth <= now.dayOfMonth) {
                        year += action.amount
                    }
                }
            }
        }
        return year
    }
}