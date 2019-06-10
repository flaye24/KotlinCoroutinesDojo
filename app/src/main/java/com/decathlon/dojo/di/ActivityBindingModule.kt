package com.decathlon.dojo.di

import com.decathlon.dojo.weather.di.WeatherPresentationModule
import com.decathlon.dojo.weather.view.WeatherForecastActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [WeatherPresentationModule::class])
    abstract fun weatherActivity() : WeatherForecastActivity
}