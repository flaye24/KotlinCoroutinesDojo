package com.decathlon.dojo.data.di

import com.decathlon.dojo.utils.schedulers.BaseSchedulerProvider
import com.decathlon.dojo.utils.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {

    @Provides
    fun provideSchedulerProvider() : BaseSchedulerProvider {
        return SchedulerProvider()
    }

}