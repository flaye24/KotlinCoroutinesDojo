package com.decathlon.dojo.data.source.remote

import android.location.Location
import com.decathlon.dojo.data.mapper.WeatherForecastMapper
import com.decathlon.dojo.data.model.DailyForecast
import retrofit2.HttpException
import javax.inject.Inject

class ForecastRemoteDataSourceImpl @Inject constructor(
    private val weatherForecastMapper: WeatherForecastMapper,
    private val forecastServices: ForecastServices
) :
    ForecastRemoteDataSource {

    override suspend fun getDailyForecasts(location: Location): List<DailyForecast> {
        val weatherForecastDTO = forecastServices.getWeatherForecast(
            "json",
            "metric",
            location.latitude,
            location.longitude,
            "application/json"
        )
        return weatherForecastMapper.convertDailyForecastDTOsToDailyForecasts(weatherForecastDTO.dailyForecastDTOs)
    }

}