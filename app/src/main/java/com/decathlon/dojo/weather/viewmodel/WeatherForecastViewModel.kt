package com.decathlon.dojo.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.source.ForecastDataSource
import com.decathlon.dojo.utils.dispatchers.CoroutineDispatcherProvider
import com.decathlon.dojo.utils.dispatchers.TestCoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val forecastDataSource: ForecastDataSource,
    //TODO : 12 replace scheduler provider by coroutine dispatcher provider
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) :
    ViewModel() {

    private val _weatherForecasts = MutableLiveData<List<DailyForecast>>()
    val weatherForecasts: LiveData<List<DailyForecast>> = _weatherForecasts
    private val _displayErrorMessage = MutableLiveData<String>()
    val displayErrorMessage: LiveData<String> = _displayErrorMessage

    //TODO : 13 - Declare coroutine job here
    private val job = SupervisorJob()


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
        forecastDataSource.refreshDailyForecasts()
        getDailyForecasts()
    }


    private fun getDailyForecasts() {
        //TODO : 14 - add CoroutineScope operating on Main Thread and use launch to get daily forecasts
        CoroutineScope(job +  coroutineDispatcherProvider.main).launch {
            try {
                val dailyForecasts = forecastDataSource.getDailyForecasts()
                _weatherForecasts.value = dailyForecasts
            } catch (exception: Throwable) {
                _displayErrorMessage.value = exception.message
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        //TODO : 15 - Cancel coroutine job when viewModel is cleared
        job.cancel()
    }

}