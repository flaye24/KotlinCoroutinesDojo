package com.decathlon.dojo.di

import com.decathlon.dojo.weather.view.WeatherForecastActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun weatherActivity(): WeatherForecastActivity
}