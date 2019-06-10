package com.decathlon.dojo.data.mapper

import android.app.Application
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.model.DailyWeatherForecastDTO
import com.decathlon.dojo.utils.SunshineDateUtils
import com.decathlon.dojo.utils.SunshineWeatherUtils
import javax.inject.Inject

class WeatherForecastMapper @Inject constructor(private val application: Application) {

    fun convertDailyForecastDTOsToDailyForecasts(dailyWeatherForecastDTOs: List<DailyWeatherForecastDTO>): List<DailyForecast> {
        return dailyWeatherForecastDTOs.map { dailyForecastDTO ->
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