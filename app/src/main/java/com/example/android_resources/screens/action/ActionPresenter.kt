package com.example.android_resources.screens.action

import android.media.Image
import android.util.Base64
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.action.adapters.ActionsAdapter
import com.example.android_resources.utils.Constants
import com.example.android_resources.utils.rxUtils.AppRxSchedulers
import com.example.android_resources.utils.rxUtils.disposeBy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class ActionPresenter(
    private val actionView: ActionView,
    private val userRepository: UserRepository,
    private val rxSchedulers: AppRxSchedulers,
    private val compositeDisposable: CompositeDisposable
) {

    fun passRecyclerData(): ArrayList<Action> {
        val list = ArrayList<Action>()
        var action = Action()
        action.category = "Income"
        list.add(action)
        action = Action()
        action.category = "Food"
        list.add(action)
        action = Action()
        action.category = "Car"
        list.add(action)
        action = Action()
        action.category = "Clothes"
        list.add(action)
        action = Action()
        action.category = "Savings"
        list.add(action)
        action = Action()
        action.category = "Health"
        list.add(action)
        action = Action()
        action.category = "Beauty"
        list.add(action)
        action = Action()
        action.category = "Travel"
        list.add(action)
        return list
    }

    fun addToDB(action: Action) {
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.background())
            .map {
                userRepository.insertAction(action)
            }
            .observeOn(rxSchedulers.androidUI())
            .doOnError {
                Timber.e(it)
            }
            .subscribe {
                Timber.d("lastId from db is %s", it)
//                actionView.saveWithLastId(it)
            }
            .disposeBy(compositeDisposable)
    }

    fun deleteActions() {
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.background())
            .map {
                userRepository.deletAllActions()
            }
            .observeOn(rxSchedulers.androidUI())
            .doOnError {
                Timber.e(it)
            }
            .subscribe {
                Timber.d("lastId from db is %s", it)
//                actionView.saveWithLastId(it)
            }
            .disposeBy(compositeDisposable)
    }

    fun initLastId() {
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.background())
            .map { userRepository.getLastId() }
            .observeOn(rxSchedulers.androidUI())
            .doOnError {
                Timber.e(it)
            }
            .subscribe {
                Timber.d("lastId from db is %s", it)
                actionView.saveWithLastId(it)
            }
            .disposeBy(compositeDisposable)
    }

    fun updateAction(action: Action) {
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.background())
            .map { userRepository.updateAction(action) }
            .doOnError {
                Timber.e(it)
            }
            .subscribe {
                Timber.d("User updated")
            }
            .disposeBy(compositeDisposable)

    }
}