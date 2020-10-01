package com.example.android_resources.screens.converter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.android_resources.app.dependecies.rxModules
import com.example.android_resources.data.api.api.ApiClient
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.utils.rxUtils.AppRxSchedulers
import com.facebook.stetho.inspector.protocol.module.Network
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.android_resources.data.api.models.Request
import com.example.android_resources.utils.Constants
import com.example.android_resources.utils.rxUtils.disposeBy
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class ConverterPresenter(
    private val converterView: ConverterView,
    private val userRepository: UserRepository,
    private val rxSchedulers: AppRxSchedulers,
    private val compositeDisposable: CompositeDisposable
) {

    fun onCreate() {
    }

    fun receiveFromAct() {
        Observable.just(Constants.EMPTY_STRING)
            .observeOn(rxSchedulers.network())
            .flatMap {
                ApiClient.converterAPI.convert()
            }
            .observeOn(rxSchedulers.androidUI())
            .doOnError { Timber.d(it.message) }
            .subscribe(
                {
                    converterView.setLeiAmount(it.rate)
                    Timber.d("KEVA %s", it.toString())
                },
                {
                Timber.d("error in subscribe") })
            .disposeBy(compositeDisposable)
    }

    fun sendFormattedDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        return current.format(formatter)
    }
}