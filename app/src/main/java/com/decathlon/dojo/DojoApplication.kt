package com.decathlon.dojo

import com.decathlon.dojo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DojoApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}