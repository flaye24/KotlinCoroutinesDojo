package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.mapper.WeatherForecastMapper
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.utils.SunshineDateUtils
import com.decathlon.dojo.utils.SunshineWeatherUtils
import io.reactivex.Single
import javax.inject.Inject

class ForecastRemoteDataSourceImpl @Inject constructor(
    private val weatherForecastMapper: WeatherForecastMapper,
    private val weatherForecastServices: WeatherForecastServices
) :
    ForecastRemoteDataSource {

    //TODO : 5 - Convert implementation

    override fun getDailyForecasts(): Single<List<DailyForecast>> {
        return weatherForecastServices.getWeatherForecast("json", "metric", "application/json")
            .map { weatherForecastDTO ->
                weatherForecastMapper.convertDailyForecastDTOsToDailyForecasts(weatherForecastDTO.dailyForecastDTOs)
            }
    }


}