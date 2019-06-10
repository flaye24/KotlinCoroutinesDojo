package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.model.WeatherForecastDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface WeatherForecastServices {

    @GET("weather")

    fun getWeatherForecast(@Query("mode") mode: String, @Query("units") unit: String,  @Header("Content-Type") contentType : String): Single<WeatherForecastDTO>
}