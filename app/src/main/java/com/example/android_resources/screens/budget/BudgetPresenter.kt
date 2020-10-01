package com.example.android_resources.screens.budget

import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.View
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.utils.Constants
import com.example.android_resources.utils.DateUtils
import com.example.android_resources.utils.rxUtils.AppRxSchedulers
import com.example.android_resources.utils.rxUtils.disposeBy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import org.threeten.bp.LocalDateTime;
import timber.log.Timber
import java.util.*

class BudgetPresenter(
    val budgetView: BudgetView,
    val userRepository: UserRepository,
    val rxSchedulers: AppRxSchedulers,
    val disposable: CompositeDisposable
) {
    fun initGetBalanceUntilDate(date: Date) {
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.background())
            .map { userRepository.getBalanceUntilDate(date) }
            .observeOn(rxSchedulers.androidUI())
            .subscribe({
                budgetView.setBalanceUntilDate(it)
            },{})
    }

    init {
        initActions()
    }

    private fun initActions() {
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.background())
            .map { userRepository.getActions() }
            .observeOn(rxSchedulers.androidUI())
            .subscribe({
                setTitles(it)
                setChart(it)
                for (action in it) {
                    Log.d("ACTION", action.toString())
                }
            }, { Timber.e(it.message) })
            .disposeBy(disposable)
    }

    fun setTitles(actions: ArrayList<Action>) {
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
                    if (date.dayOfMonth + 7 > now.dayOfMonth)
                        week += action.amount
                }
            } else if (date.plusMonths(1).month == now.month) {
                if (date.dayOfMonth > now.minusDays(7).dayOfMonth) {
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

    fun setChart(actions: ArrayList<Action>) {
        val v = budgetView.layout
        val data = BarData(actions, budgetView.getResources())
        var chartData = data.createChartData()
        data.configureChartAppearance(v)
        data.prepareChartData(v, chartData)
    }
}