package com.decathlon.dojo.data.source

import android.location.Location
import com.decathlon.dojo.data.model.DailyForecast
import io.reactivex.Single

/**
 * Main entry point to access weather forecast data
 */
interface ForecastDataSource {
    /**
     * Gets the weather daily forecast for the next x days from the api
     * @param location : the current location
     *
     * @return a [List] of [DailyForecast]
     */
    suspend fun getDailyForecasts(location : Location): List<DailyForecast>


    /**
     * Clears cache
     */
    fun invalidateForecastsCache()

}