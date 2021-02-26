package com.sarafaria.android.github.configs.api

import com.sarafaria.android.github.configs.environmenttype.Environment

object BaseUrl {

    // STAGE
    private const val GITHUB_STAGE_ENDPOINT = "https://api.github.com/"

    // PROD and PILOT
    private const val GITHUB_PRODUCTION_ENDPOINT = "https://api.github.com/"

    @JvmStatic
    fun getGithubUrl(environment: Environment): String {
        return when(environment) {
            Environment.STAGE -> GITHUB_STAGE_ENDPOINT
            else -> GITHUB_PRODUCTION_ENDPOINT
        }
    }
}