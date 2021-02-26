package com.sarafaria.android.github.configs.environmenttype

import com.sarafaria.android.github.BuildConfig

object BuildType {

    fun getEnvironmentBuildType() : Environment {
        return when (BuildConfig.BUILD_TYPE) {
            "debug" -> Environment.STAGE
            else -> Environment.PRODUCTION
        }
    }

}