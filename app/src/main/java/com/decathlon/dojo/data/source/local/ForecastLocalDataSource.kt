package com.decathlon.dojo.data.source.local

import com.decathlon.dojo.data.model.DailyForecast
import io.reactivex.Completable
import io.reactivex.Single

interface ForecastLocalDataSource {

    //TODO : 7 - Converts to suspend function which returns a List of DailyForecasts

    /**
     * Gets the weather daily forecast for the next x days from the local database
     *
     * @return a [List] of [DailyForecast]
     */
    suspend fun getDailyForecasts(): List<DailyForecast>


    //TODO : 8 - Converts to suspend function and remove return type

    /**
     * Saves the weather daily forecast in the local database
     * @param weatherForecastToSave : a [List] of [DailyForecast] to save
     *
     */
    suspend fun saveDailyForecasts(weatherForecastToSave: List<DailyForecast>)

}