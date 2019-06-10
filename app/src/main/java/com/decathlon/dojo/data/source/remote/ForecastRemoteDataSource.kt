package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.model.DailyForecast

interface ForecastRemoteDataSource {

    //TODO : 4 - Converts to suspend function which returns a List of DailyForecasts

    /**
     * Gets the weather daily forecast for the next x days from the api
     *
     * @return a [List] of [DailyForecast]
     */
    suspend fun getDailyForecasts(): List<DailyForecast>
}