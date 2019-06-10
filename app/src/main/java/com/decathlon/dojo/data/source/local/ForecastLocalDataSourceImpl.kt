package com.decathlon.dojo.data.source.local

import com.decathlon.dojo.data.model.DailyForecast
import javax.inject.Inject

class ForecastLocalDataSourceImpl @Inject constructor(private val weatherDatabase: WeatherDatabase) :
    ForecastLocalDataSource {


    //TODO : 9 - Convert implementation

    override suspend fun saveDailyForecasts(weatherForecastToSave: List<DailyForecast>) {
        weatherDatabase.getWeatherForecastDao().deleteAllDailyForecasts()
        return weatherDatabase.getWeatherForecastDao().insertDailyForecasts(weatherForecastToSave)
    }

    override suspend fun getDailyForecasts(): List<DailyForecast> {
        return weatherDatabase.getWeatherForecastDao().getDailyForecasts()
    }

}