package com.example.android_resources.screens.expenses.expensesUI

import android.content.res.Resources
import android.util.Log
import android.view.View
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.expenses.PieChart
import com.example.android_resources.utils.Constants
import com.example.android_resources.utils.DateUtils
import com.example.android_resources.utils.rxUtils.AppRxSchedulers
import com.example.android_resources.utils.rxUtils.disposeBy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.threeten.bp.LocalDateTime
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class ExpensesUIPresenter(
    val expensesUIView: ExpensesUIView,
    val userRepository: UserRepository,
    val rxSchedulers: AppRxSchedulers,
    val disposable: CompositeDisposable
) {
    var list = ArrayList<Action>()
    fun initGetActionsFromDB(title: Any?, resources: Resources) {
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.background())
            .map { userRepository.getActions() }
            .observeOn(rxSchedulers.androidUI())
            .doOnNext {
                list = it
            }
            .doOnComplete { initGetBalanceUntilDate(title, resources) }
            .subscribe()
            .disposeBy(disposable)
    }

    fun chart(v: View, title: Any?) {
        if (title == null)
            return
        val pieChart = PieChart(adapterList(title))
        pieChart.instantiate(v)
    }

    fun initGetBalanceUntilDate(title: Any?, resources: Resources) {
        var balanceList = ArrayList<Double>()
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.background())
            .map {
                for (action in list) {
                    balanceList.add(userRepository.getBalanceUntilDate(action.date))
                }
            }
            .observeOn(rxSchedulers.androidUI())
            .doOnComplete {
            }
            .subscribe({
                chart(expensesUIView.layout, title)
                expensesUIView.setAdapter(title, adapterList(title), balanceList, resources)
            }, {
                Timber.d("")
            }, {
            })
            .disposeBy(disposable)
    }

    private fun adapterList(title: Any?): ArrayList<Action> {
        val finalArray = ArrayList<Action>()
        if (title == null)
            return ArrayList()
        if (list.size == 0) {
            Timber.d("LIST IS NULL")
        }
        when (title.toString()) {
            "week" -> {
                weekCase(list, finalArray)
            }
            "month" -> {
                monthCase(list, finalArray)
            }
            "year" -> {
                yearCase(list, finalArray)
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
                    if (date.dayOfMonth == now.dayOfMonth) {
                        if (date.dayOfMonth <= now.dayOfMonth) {
                            if (date.dayOfMonth + 7 > now.dayOfMonth) {
                                Timber.d(
                                    "now.minusDays(7).dayOfMonth is %s",
                                    now.minusDays(7).dayOfMonth
                                )
                                finalArray.add(action)
                            }
                        }
                    }
                } else if (date.plusMonths(1).month == now.month) {
                    if (date.dayOfMonth > now.minusDays(7).dayOfMonth) {
                        Timber.d("now.minusDays(7).dayOfMonth is %s", now.minusDays(7).dayOfMonth)
                        finalArray.add(action)
                    }
                }
            }
        }
    }
}