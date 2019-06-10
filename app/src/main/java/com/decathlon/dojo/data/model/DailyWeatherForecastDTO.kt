package com.decathlon.dojo.data.model

import com.google.gson.annotations.SerializedName

data class DailyWeatherForecastDTO(
    @SerializedName("dt") val date: Long,
    @SerializedName("temp") val temperature: TemperatureDTO,
    @SerializedName("weather") val weatherList: ArrayList<WeatherDTO>
)