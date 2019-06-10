package com.decathlon.dojo.data.model

import com.google.gson.annotations.SerializedName

data class WeatherForecastDTO(
    @SerializedName("cod") val code: String,
    @SerializedName("list") val dailyForecastDTOs: List<DailyWeatherForecastDTO>
)