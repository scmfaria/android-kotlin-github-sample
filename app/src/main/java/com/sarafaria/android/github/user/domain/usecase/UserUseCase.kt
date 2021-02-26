package com.sarafaria.android.github.user.domain.usecase

import com.sarafaria.android.github.user.domain.model.User
import io.reactivex.Single

interface UserUseCase {

    fun getAllUsers(): Single<List<User>>

    fun getUserByUsername(username: String): Single<User>
}