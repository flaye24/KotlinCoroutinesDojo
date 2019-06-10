package com.decathlon.dojo.data.di

import android.app.Application
import androidx.room.Room
import com.decathlon.dojo.data.source.ForecastDataSource
import com.decathlon.dojo.data.source.ForecastRepository
import com.decathlon.dojo.data.source.local.WEATHER_DATABASE_NAME
import com.decathlon.dojo.data.source.local.WeatherDatabase
import com.decathlon.dojo.data.source.local.ForecastLocalDataSource
import com.decathlon.dojo.data.source.local.ForecastLocalDataSourceImpl
import com.decathlon.dojo.data.source.remote.ForecastRemoteDataSource
import com.decathlon.dojo.data.source.remote.ForecastRemoteDataSourceImpl
import com.decathlon.dojo.data.source.remote.WeatherForecastServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class WeatherForecastRepositoryModule {

    @Provides
    fun provideWeatherForecastRemoteDataSource(weatherForecastRemoteDataSourceImpl: ForecastRemoteDataSourceImpl): ForecastRemoteDataSource =
        weatherForecastRemoteDataSourceImpl

    @Provides
    fun provideWeatherForecastLocalDataSource(weatherForecastLocalDataSourceImpl: ForecastLocalDataSourceImpl): ForecastLocalDataSource =
        weatherForecastLocalDataSourceImpl

    @Provides
    fun provideWeatherForecastDataSource(weatherForecastRepository: ForecastRepository): ForecastDataSource =
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