package com.sarafaria.android.github.user.data

import com.sarafaria.android.github.user.data.entity.UserEntity
import com.sarafaria.android.github.user.domain.UserRepository
import com.sarafaria.android.github.user.data.datasource.UserDataSource
import io.reactivex.Single

class UserRepositoryImpl(
    val mDataSource: UserDataSource) : UserRepository {

    override fun getAllUsers(): Single<List<UserEntity>> {
        return mDataSource.getAllUsers()
    }

    override fun getUserByUsername(username: String): Single<UserEntity> {
        return mDataSource.getUserByUsername(username)
    }
}