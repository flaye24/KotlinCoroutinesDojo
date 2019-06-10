package com.decathlon.dojo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.decathlon.dojo.data.model.DailyForecast

const val WEATHER_DATABASE_NAME = "weather_database"

@Database(entities = [DailyForecast::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeatherForecastDao(): WeatherForecastDao

}