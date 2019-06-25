package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.model.DailyForecast

interface ForecastRemoteDataSource {
    /**
     * Gets the weather daily forecast for the next x days from the api
     *
     * @return a [List] of [DailyForecast]
     */
    suspend fun getDailyForecasts(): List<DailyForecast>

    /**
     * Gets the weather daily forecast for the next x days from the api synchronously
     *
     *
     * @return a [List] of [DailyForecast]
     */
    fun getDailyForecastsSync(): List<DailyForecast>
}