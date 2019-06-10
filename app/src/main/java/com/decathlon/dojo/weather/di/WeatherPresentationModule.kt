package com.decathlon.dojo.weather.di

import com.decathlon.dojo.data.source.WeatherForecastDataSource
import com.decathlon.dojo.utils.schedulers.BaseSchedulerProvider
import com.decathlon.dojo.weather.viewmodel.WeatherForecastViewModel
import com.decathlon.dojo.weather.viewmodel.WeatherViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class WeatherPresentationModule {

    @Provides
    fun provideViewModelFactory(forecastViewModel: WeatherForecastViewModel): WeatherViewModelFactory {
        return WeatherViewModelFactory(forecastViewModel)
    }

    @Provides
    fun provideViewModel(
        weatherForecastDataSource: WeatherForecastDataSource,
        baseSchedulerProvider: BaseSchedulerProvider
    ): WeatherForecastViewModel {
        return WeatherForecastViewModel(weatherForecastDataSource, baseSchedulerProvider)
    }
}