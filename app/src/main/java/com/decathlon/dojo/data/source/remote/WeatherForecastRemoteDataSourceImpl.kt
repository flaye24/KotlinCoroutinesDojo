package com.decathlon.dojo.data.source.remote

import android.app.Application
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.model.DailyWeatherForecastDTO
import com.decathlon.dojo.utils.SunshineDateUtils
import com.decathlon.dojo.utils.SunshineWeatherUtils
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastRemoteDataSourceImpl @Inject constructor(
    private val application: Application,
    private val weatherForecastServices: WeatherForecastServices
) :
    WeatherForecastRemoteDataSource {

    override fun getDailyForecasts(): Single<List<DailyForecast>> {
        return weatherForecastServices.getWeatherForecast("json", "metric", "application/json")
            .map { weatherForecastDTO ->
                weatherForecastDTO.dailyForecastDTOList.map { dailyForecastDTO ->
                    val formattedDate =
                        SunshineDateUtils.getFriendlyDateString(dailyForecastDTO.date)
                    val minTemp =
                        SunshineWeatherUtils.formatTemperature(application, dailyForecastDTO.temperature.min)
                    val maxTemp =
                        SunshineWeatherUtils.formatTemperature(application, dailyForecastDTO.temperature.max)
                    val weatherDescription = SunshineWeatherUtils.getStringForWeatherCondition(
                        application,
                        dailyForecastDTO.weatherList.first().weatherId
                    )
                    val weatherResId =
                        SunshineWeatherUtils.getLargeArtResourceIdForWeatherCondition(dailyForecastDTO.weatherList.first().weatherId)

                    DailyForecast(
                        formattedDate,
                        minTemp,
                        maxTemp,
                        weatherDescription,
                        weatherResId
                    )
                }


            }
    }
}