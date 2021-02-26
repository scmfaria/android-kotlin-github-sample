package com.sarafaria.android.github.user.domain

import com.sarafaria.android.github.user.data.entity.UserEntity
import io.reactivex.Single

interface UserRepository {

    fun getAllUsers(): Single<List<UserEntity>>

    fun getUserByUsername(username: String): Single<UserEntity>
}