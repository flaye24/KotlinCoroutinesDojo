package com.decathlon.dojo.data.source.local

import com.decathlon.dojo.data.model.DailyForecast
import javax.inject.Inject

class ForecastLocalDataSourceImpl @Inject constructor(private val weatherDatabase: WeatherDatabase) :
    ForecastLocalDataSource {


    override suspend fun saveDailyForecasts(weatherForecastToSave: List<DailyForecast>) {
        weatherDatabase.getWeatherForecastDao().deleteAllDailyForecasts()
        return weatherDatabase.getWeatherForecastDao().insertDailyForecasts(weatherForecastToSave)
    }

    override suspend fun getDailyForecasts(): List<DailyForecast> {
        return weatherDatabase.getWeatherForecastDao().getDailyForecasts()
    }


    override fun saveDailyForecastsSync(weatherForecastToSave: List<DailyForecast>) {
        weatherDatabase.getWeatherForecastDao().deleteAllDailyForecastsSync()
        return weatherDatabase.getWeatherForecastDao().insertDailyForecastsSync(weatherForecastToSave)
    }

    override fun getDailyForecastsSync(): List<DailyForecast> {
        return weatherDatabase.getWeatherForecastDao().getDailyForecastsSync()
    }

}