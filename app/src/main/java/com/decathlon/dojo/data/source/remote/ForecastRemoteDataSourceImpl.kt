package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.mapper.WeatherForecastMapper
import com.decathlon.dojo.data.model.DailyForecast
import javax.inject.Inject

class ForecastRemoteDataSourceImpl @Inject constructor(
    private val weatherForecastMapper: WeatherForecastMapper,
    private val forecastServices: ForecastServices
) :
    ForecastRemoteDataSource {

    //TODO : 5 - Convert implementation

    override suspend fun getDailyForecasts(): List<DailyForecast> {
        val weatherForecastDTO = forecastServices.getWeatherForecast("json", "metric", "application/json")
        return weatherForecastMapper.convertDailyForecastDTOsToDailyForecasts(weatherForecastDTO.dailyForecastDTOs)
    }


}