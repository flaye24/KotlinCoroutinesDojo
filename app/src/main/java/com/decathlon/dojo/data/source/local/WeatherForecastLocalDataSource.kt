package com.decathlon.dojo.data.source.local

import com.decathlon.dojo.data.model.DailyForecast
import io.reactivex.Completable
import io.reactivex.Single

interface WeatherForecastLocalDataSource {

    /**
     * Gets the weather daily forecast for the next x days from the local database
     *
     * @return a [Single] with a [List] of [DailyForecast]
     */
    fun getDailyForecasts(): Single<List<DailyForecast>>


    /**
     * Saves the weather daily forecast in the local database
     *
     * @return a [Completable]
     */
    fun saveDailyForecasts(weatherForecastToSave: List<DailyForecast>): Completable

}