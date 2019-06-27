package com.decathlon.dojo.weather.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.source.ForecastDataSource
import com.decathlon.dojo.utils.LocationManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val forecastDataSource: ForecastDataSource,
    private val locationManager: LocationManager
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


    //TODO : 3 remove callbacks and call new suspend getLastKnowLocation in launch coroutine
    @SuppressLint("MissingPermission")
    private fun getDailyForecasts() {
        viewModelScope.launch {
            try {
                val location = locationManager.getLastKnowLocation()
                val dailyForecasts = forecastDataSource.getDailyForecasts(location)
                _weatherForecasts.postValue(dailyForecasts)
            } catch (exception: Throwable) {
                _displayErrorMessage.value = exception.message
            }
        }
    }
}