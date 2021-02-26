package com.sarafaria.android.github.di.modules.networking

import com.sarafaria.android.github.configs.api.ApiClient
import com.sarafaria.android.github.configs.api.BaseUrl
import com.sarafaria.android.github.configs.environmenttype.BuildType
import com.sarafaria.android.github.utils.ioThread
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideRetrofitApi(): Retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl.getGithubUrl(BuildType.getEnvironmentBuildType()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(ioThread()))
        .addConverterFactory(GsonConverterFactory.create())
        .client(ApiClient.createHttpClient())
        .build()
}