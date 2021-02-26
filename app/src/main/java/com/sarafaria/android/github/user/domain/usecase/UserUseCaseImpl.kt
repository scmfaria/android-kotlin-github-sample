package com.sarafaria.android.github.user.domain.usecase

import com.sarafaria.android.github.user.domain.UserRepository
import com.sarafaria.android.github.user.domain.model.User
import com.sarafaria.android.github.user.domain.model.toUser
import com.sarafaria.android.github.user.domain.model.toUsers
import io.reactivex.Single

class UserUseCaseImpl(
    val mRepository: UserRepository) : UserUseCase {

    override fun getAllUsers(): Single<List<User>> {
        return mRepository.getAllUsers()
            .map { it.toUsers() }
    }

    override fun getUserByUsername(username: String): Single<User> {
        return mRepository.getUserByUsername(username)
            .map { it.toUser() }
    }
}