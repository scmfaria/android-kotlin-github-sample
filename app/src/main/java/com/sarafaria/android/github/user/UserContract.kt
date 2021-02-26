package com.sarafaria.android.github.user

import com.sarafaria.android.github.user.domain.model.User

interface UserContract {

    interface View {
        fun loadUsers(user: List<User>)
        fun dismissProgressbar()
        fun dismissContainerMessage()
        fun showContainerMessage()
        fun showRecyclerView()
        fun showMessage(message: String)
        fun showMessage(resMessage: Int)
        fun showToastMessage(resMessage: Int)
    }

    interface Presenter {
        fun searchUsers()
        fun searchUserByUsername(username: String)
    }
}