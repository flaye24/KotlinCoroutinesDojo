package com.decathlon.dojo.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decathlon.dojo.data.source.WeatherForecastDataSource
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.utils.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val weatherForecastDataSource: WeatherForecastDataSource,
    private val baseSchedulerProvider: BaseSchedulerProvider
) :
    ViewModel() {

    private val _weatherForecasts = MutableLiveData<List<DailyForecast>>()
    val weatherForecasts: LiveData<List<DailyForecast>> = _weatherForecasts
    private val _displayErrorMessage = MutableLiveData<String>()
    val displayErrorMessage: LiveData<String> = _displayErrorMessage

    private val compositeDisposable = CompositeDisposable()


    /**
     * Loads weather forecasts from repository
     */
    fun loadWeatherForecast() {
        getDailyForecasts()
    }

    /**
     * Refreshes and loads weather forecasts from repository
     */
    fun onRefresh() {
        weatherForecastDataSource.refreshDailyForecasts()
        getDailyForecasts()
    }


    private fun getDailyForecasts() {
        weatherForecastDataSource.getDailyForecasts()
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
    }

}