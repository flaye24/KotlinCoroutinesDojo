package com.decathlon.dojo.di

import android.app.Application
import com.decathlon.dojo.DojoApplication
import com.decathlon.dojo.data.di.SchedulersModule
import com.decathlon.dojo.data.di.ForecastRepositoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.BindsInstance

@Component(
    modules = [AndroidInjectionModule::class,
        ActivityBindingModule::class,
        NetworkModule::class,
        SchedulersModule::class,
        ForecastRepositoryModule::class]
)
interface AppComponent : AndroidInjector<DojoApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}