package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.mapper.WeatherForecastMapper
import com.decathlon.dojo.data.model.DailyForecast
import io.reactivex.Single
import javax.inject.Inject

class ForecastRemoteDataSourceImpl @Inject constructor(
    private val weatherForecastMapper: WeatherForecastMapper,
    private val forecastServices: ForecastServices
) :
    ForecastRemoteDataSource {

    //TODO : 5 - Convert implementation

    override fun getDailyForecasts(): Single<List<DailyForecast>> {
        return forecastServices.getWeatherForecast("json", "metric", "application/json")
            .map { weatherForecastDTO ->
                weatherForecastMapper.convertDailyForecastDTOsToDailyForecasts(weatherForecastDTO.dailyForecastDTOs)
            }
    }


}