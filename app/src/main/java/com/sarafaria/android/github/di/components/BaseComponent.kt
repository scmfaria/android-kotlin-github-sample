package com.sarafaria.android.github.di.components

import com.sarafaria.android.github.application.AppController
import com.sarafaria.android.github.di.modules.user.UserModule
import com.sarafaria.android.github.di.android.ActivityBuilderModule
import com.sarafaria.android.github.di.modules.AppModule
import com.sarafaria.android.github.di.modules.networking.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ActivityBuilderModule::class,
    UserModule::class,
    RetrofitModule::class
])
interface BaseComponent : AndroidInjector<AppController> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance instance: AppController): AndroidInjector<AppController>?
    }

}