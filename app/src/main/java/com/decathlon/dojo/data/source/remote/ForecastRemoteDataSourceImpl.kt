package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.mapper.WeatherForecastMapper
import com.decathlon.dojo.data.model.DailyForecast
import retrofit2.HttpException
import javax.inject.Inject

class ForecastRemoteDataSourceImpl @Inject constructor(
    private val weatherForecastMapper: WeatherForecastMapper,
    private val forecastServices: ForecastServices
) :
    ForecastRemoteDataSource {

    override suspend fun getDailyForecasts(): List<DailyForecast> {
        val weatherForecastDTO = forecastServices.getWeatherForecast("json", "metric", "application/json")
        return weatherForecastMapper.convertDailyForecastDTOsToDailyForecasts(weatherForecastDTO.dailyForecastDTOs)
    }

}