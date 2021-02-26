package com.sarafaria.android.github.user.data.api

import com.sarafaria.android.github.user.data.entity.UserEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users")
    fun getAllUsers(): Single<List<UserEntity>>

    @GET("users/{username}")
    fun getUserByUsername(@Path("username") username: String): Single<UserEntity>
}