package com.decathlon.dojo.data.source.local

import com.decathlon.dojo.data.model.DailyForecast
import io.reactivex.Completable
import io.reactivex.Single

interface ForecastLocalDataSource {


    /**
     * Gets the weather daily forecast for the next x days from the local database
     *
     * @return a [List] of [DailyForecast]
     */
    suspend fun getDailyForecasts(): List<DailyForecast>


    /**
     * Saves the weather daily forecast in the local database
     * @param weatherForecastToSave : a [List] of [DailyForecast] to save
     *
     */
    suspend fun saveDailyForecasts(weatherForecastToSave: List<DailyForecast>)

    /**
     * Saves the weather daily forecast in the local database synchronously
     * @param weatherForecastToSave : a [List] of [DailyForecast] to save
     *
     */
    fun saveDailyForecastsSync(weatherForecastToSave: List<DailyForecast>)

    /**
     * Gets the weather daily forecast for the next x days from the local database synchronously
     *
     * @return a [List] of [DailyForecast]
     */
    fun getDailyForecastsSync(): List<DailyForecast>

}