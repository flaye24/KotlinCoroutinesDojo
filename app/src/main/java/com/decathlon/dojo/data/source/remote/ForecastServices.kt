package com.decathlon.dojo.data.source.remote

import com.decathlon.dojo.data.model.WeatherForecastDTO
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ForecastServices {

    @GET("weather")
    suspend fun getWeatherForecast(
        @Query("mode") mode: String, @Query("units") unit: String, @Query("lat") latitude: Double, @Query(
            "lon"
        ) longitude: Double, @Header("Content-Type") contentType: String
    ): WeatherForecastDTO

}