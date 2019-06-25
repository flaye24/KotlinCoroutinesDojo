package com.decathlon.dojo.weather.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decathlon.dojo.data.source.ForecastDataSource
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.utils.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val forecastDataSource: ForecastDataSource,
    //TODO : 12 remove scheduler
    private val baseSchedulerProvider: BaseSchedulerProvider
) :
    ViewModel() {

    private val _weatherForecasts = MutableLiveData<List<DailyForecast>>()
    val weatherForecasts: LiveData<List<DailyForecast>> = _weatherForecasts
    private val _displayErrorMessage = MutableLiveData<String>()
    val displayErrorMessage: LiveData<String> = _displayErrorMessage

    private val compositeDisposable = CompositeDisposable()

    //TODO : 13 - Declare coroutine job here


    /**
     * Loads weather forecasts from repository
     */
    @SuppressLint("MissingPermission")
    fun loadWeatherForecast() {
        getDailyForecasts()
    }

    /**
     * Refreshes and loads weather forecasts from repository
     */
    fun onRefresh() {
        forecastDataSource.invalidateForecastsCache()
        getDailyForecasts()
    }


    private fun getDailyForecasts() {
        //TODO : 14 - add CoroutineScope operating on Main Thread and use launch to get daily forecasts
        forecastDataSource.getDailyForecasts()
            .subscribeOn(baseSchedulerProvider.io())
            .observeOn(baseSchedulerProvider.ui())
            .subscribe(
                { dailyForecasts ->
                    _weatherForecasts.value = dailyForecasts
                }, { error ->
                    _displayErrorMessage.value = error.message
                }
            ).also { disposable ->
                compositeDisposable.add(disposable)
            }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        //TODO : 15 - Cancel coroutine job when viewModel is cleared
    }

}