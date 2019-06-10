package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.model.DailyWeatherForecastDTO
import io.reactivex.Single

interface WeatherForecastRemoteDataSource {

    /**
     * Gets the weather daily forecast for the next x days from the api
     *
     * @return a [Single] with a [List] of [DailyForecast]
     */
    fun getDailyForecasts(): Single<List<DailyForecast>>

}