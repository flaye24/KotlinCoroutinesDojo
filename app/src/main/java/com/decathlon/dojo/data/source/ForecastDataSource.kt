package com.decathlon.dojo.data.source

import com.decathlon.dojo.data.model.DailyForecast
import io.reactivex.Single

/**
 * Main entry point to access weather forecast data
 */
interface ForecastDataSource {

    //TODO : 10 - Convert to suspend function and change return type

    /**
     * Gets the weather daily forecast for the next x days from the api
     *
     * @return a [List] of [DailyForecast]
     */
    suspend fun getDailyForecasts(): List<DailyForecast>


    /**
     * Clears cache
     */
    fun refreshDailyForecasts()

}