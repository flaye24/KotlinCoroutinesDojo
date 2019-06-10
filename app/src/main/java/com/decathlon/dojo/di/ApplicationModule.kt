package com.decathlon.dojo.di

import android.content.Context
import com.decathlon.dojo.DojoApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(dojoApplication: DojoApplication) : Context
}