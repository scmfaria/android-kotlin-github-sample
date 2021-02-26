package com.sarafaria.android.github.user.domain.model

import com.sarafaria.android.github.user.data.entity.UserEntity


fun List<UserEntity>.toUsers() : List<User> =
    map {
        it.toUser()
    }

fun UserEntity.toUser() = User(
    userName = username,
    imageUrl = imageUrl
)