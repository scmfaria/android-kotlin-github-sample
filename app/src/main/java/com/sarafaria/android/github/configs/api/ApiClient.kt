package com.sarafaria.android.github.configs.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import com.sarafaria.android.github.BuildConfig

object ApiClient {

    fun createHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(2, TimeUnit.MINUTES)
        httpClient.writeTimeout(2, TimeUnit.MINUTES)
        httpClient.connectTimeout(2, TimeUnit.MINUTES)

        httpClient.addInterceptor(createLogInterceptor())

        return httpClient.build()
    }

    private fun createLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            when(BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        return interceptor
    }

}