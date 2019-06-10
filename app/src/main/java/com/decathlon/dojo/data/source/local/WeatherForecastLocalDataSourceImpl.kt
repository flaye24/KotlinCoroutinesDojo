package com.decathlon.dojo.data.source.local

import com.decathlon.dojo.data.model.DailyForecast
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastLocalDataSourceImpl @Inject constructor(private val weatherDatabase: WeatherDatabase) :
    WeatherForecastLocalDataSource {

    override fun saveDailyForecasts(weatherForecastToSave: List<DailyForecast>): Completable {
        return weatherDatabase.getWeatherForecastDao().deleteAllDailyForecasts()
            .andThen(weatherDatabase.getWeatherForecastDao().insertDailyForecasts(weatherForecastToSave))
    }

    override fun getDailyForecasts(): Single<List<DailyForecast>> {
        return weatherDatabase.getWeatherForecastDao().getDailyForecasts()
    }

}