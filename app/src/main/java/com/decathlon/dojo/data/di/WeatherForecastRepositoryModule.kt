package com.decathlon.dojo.data.di

import android.app.Application
import androidx.room.Room
import com.decathlon.dojo.data.source.WeatherForecastDataSource
import com.decathlon.dojo.data.source.WeatherForecastRepository
import com.decathlon.dojo.data.source.local.WEATHER_DATABASE_NAME
import com.decathlon.dojo.data.source.local.WeatherDatabase
import com.decathlon.dojo.data.source.local.WeatherForecastLocalDataSource
import com.decathlon.dojo.data.source.local.WeatherForecastLocalDataSourceImpl
import com.decathlon.dojo.data.source.remote.WeatherForecastRemoteDataSource
import com.decathlon.dojo.data.source.remote.WeatherForecastRemoteDataSourceImpl
import com.decathlon.dojo.data.source.remote.WeatherForecastServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class WeatherForecastRepositoryModule {

    @Provides
    fun provideWeatherForecastRemoteDataSource(weatherForecastRemoteDataSourceImpl: WeatherForecastRemoteDataSourceImpl): WeatherForecastRemoteDataSource =
        weatherForecastRemoteDataSourceImpl

    @Provides
    fun provideWeatherForecastLocalDataSource(weatherForecastLocalDataSourceImpl: WeatherForecastLocalDataSourceImpl): WeatherForecastLocalDataSource =
        weatherForecastLocalDataSourceImpl

    @Provides
    fun provideWeatherForecastDataSource(weatherForecastRepository: WeatherForecastRepository): WeatherForecastDataSource =
        weatherForecastRepository


    @Provides
    fun provideWeatherForecastServices(retrofit: Retrofit): WeatherForecastServices {
        return retrofit.create(WeatherForecastServices::class.java)
    }

    @Provides
    fun provideWeatherDatabase(application : Application) : WeatherDatabase{
        return Room.databaseBuilder(
            application, WeatherDatabase::class.java,
            WEATHER_DATABASE_NAME
        ).build()
    }
}