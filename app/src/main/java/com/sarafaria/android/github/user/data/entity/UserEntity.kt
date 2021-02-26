package com.sarafaria.android.github.user.data.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("avatar_url") val imageUrl: String?,
    @SerializedName("login") val username: String?
)