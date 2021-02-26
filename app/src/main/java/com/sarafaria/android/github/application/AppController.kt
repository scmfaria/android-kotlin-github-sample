package com.sarafaria.android.github.application

import android.app.Application
import com.sarafaria.android.github.di.components.AppComponents.initComponents
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppController: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initComponents(this)
    }

    override fun androidInjector() = androidInjector
}