package com.sarafaria.android.github.user.mock

import com.sarafaria.android.github.user.data.entity.UserEntity
import com.sarafaria.android.github.user.domain.model.User
import net.andreinc.mockneat.MockNeat

object UserMock {

    val userMock = MockNeat.threadLocal().let {
        User(
            imageUrl = it.urls().get(),
            userName = it.users().get()
        )
    }

    val userEntityMock = MockNeat.threadLocal().let {
        UserEntity(
            username = it.users().get(),
            imageUrl = it.urls().get()
        )
    }
}