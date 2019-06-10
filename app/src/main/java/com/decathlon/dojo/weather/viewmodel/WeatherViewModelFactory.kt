package com.decathlon.dojo.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class WeatherViewModelFactory @Inject constructor(private val weatherForecastViewModel: WeatherForecastViewModel) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return weatherForecastViewModel as T
    }
}