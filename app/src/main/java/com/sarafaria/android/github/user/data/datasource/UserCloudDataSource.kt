package com.sarafaria.android.github.user.data.datasource

import com.sarafaria.android.github.user.data.entity.UserEntity
import com.sarafaria.android.github.user.data.api.UserService
import io.reactivex.Single

class UserCloudDataSource(
    private val mService: UserService) : UserDataSource {

    override fun getAllUsers(): Single<List<UserEntity>> {
        return mService.getAllUsers()
    }

    override fun getUserByUsername(username: String): Single<UserEntity> {
        return mService.getUserByUsername(username)
    }
}