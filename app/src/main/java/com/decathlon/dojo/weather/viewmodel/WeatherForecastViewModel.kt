package com.decathlon.dojo.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.source.ForecastDataSource
import com.decathlon.dojo.utils.dispatchers.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val forecastDataSource: ForecastDataSource
) :
    ViewModel() {

    private val _weatherForecasts = MutableLiveData<List<DailyForecast>>()
    val weatherForecasts: LiveData<List<DailyForecast>> = _weatherForecasts
    private val _displayErrorMessage = MutableLiveData<String>()
    val displayErrorMessage: LiveData<String> = _displayErrorMessage


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
        forecastDataSource.invalidateForecastsCache()
        getDailyForecasts()
    }


    private fun getDailyForecasts() {
        viewModelScope.launch {
            try {
                val dailyForecasts = forecastDataSource.getDailyForecasts()
                _weatherForecasts.value = dailyForecasts
            } catch (exception: Throwable) {
                _displayErrorMessage.value = exception.message
            }
        }
    }

}