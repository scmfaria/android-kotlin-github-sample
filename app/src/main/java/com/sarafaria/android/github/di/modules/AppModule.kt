package com.sarafaria.android.github.di.modules

import android.app.Application
import android.content.Context
import com.sarafaria.android.github.application.AppController
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {
    @[Binds Singleton]
    abstract fun application(app: AppController): Application

    @[Binds Singleton]
    abstract fun context(app: AppController): Context
}