package com.decathlon.dojo.data.source.remote

import android.location.Location
import com.decathlon.dojo.data.model.DailyForecast

interface ForecastRemoteDataSource {
    /**
     * Gets the weather daily forecast for the next x days from the api
     * @param location : the current location
     *
     * @return a [List] of [DailyForecast]
     */
    suspend fun getDailyForecasts(location: Location): List<DailyForecast>
}