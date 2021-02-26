package com.sarafaria.android.github.user.data.datasource

import com.sarafaria.android.github.user.data.entity.UserEntity
import io.reactivex.Single

interface UserDataSource {

    fun getAllUsers(): Single<List<UserEntity>>

    fun getUserByUsername(username: String): Single<UserEntity>
}