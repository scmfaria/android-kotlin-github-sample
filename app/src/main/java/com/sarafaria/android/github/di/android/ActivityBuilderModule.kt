package com.sarafaria.android.github.di.android

import com.sarafaria.android.github.di.modules.user.UserModule
import com.sarafaria.android.github.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun contributeUserActivity(): UserActivity
}