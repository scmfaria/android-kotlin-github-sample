package com.sarafaria.android.github.user

import com.sarafaria.android.github.R
import com.sarafaria.android.github.di.base.BasePresenter
import com.sarafaria.android.github.user.domain.model.User
import com.sarafaria.android.github.user.domain.usecase.UserUseCase
import com.sarafaria.android.github.utils.androidThread
import com.sarafaria.android.github.utils.ioThread

class UserPresenter(
    private val mUseCase: UserUseCase) : UserContract.Presenter, BasePresenter<UserContract.View>() {

    override fun searchUsers() {
        view()?.showContainerMessage()
        view()?.showMessage(R.string.message_wait_process)
        subscription().add(
            mUseCase.getAllUsers()
                .subscribeOn(ioThread())
                .observeOn(androidThread())
                .doFinally {
                    view()?.dismissProgressbar()
                }
                .subscribe ({ users ->
                    view()?.dismissContainerMessage()
                    view()?.showRecyclerView()
                    users?.let {
                        view()?.loadUsers(users)
                    }
                }, { throwable ->
                    val throwableMessage = throwable.message
                    if(throwableMessage.isNullOrEmpty()) {
                        view()?.showMessage(R.string.message_error_search_users)
                    } else {
                        view()?.showMessage(throwableMessage)
                    }
                })
        )
    }

    override fun searchUserByUsername(username: String) {
        subscription().add(
            mUseCase.getUserByUsername(username)
                .subscribeOn(ioThread())
                .observeOn(androidThread())
                .doFinally {
                    view()?.dismissProgressbar()
                }
                .subscribe ({ user: User? ->
                    view()?.showRecyclerView()
                    if(user != null) {
                        val userList: MutableList<User> = ArrayList()
                        userList.add(user)
                        view()?.loadUsers(userList)
                    } else {
                        view()?.showToastMessage(R.string.message_search_not_found)
                    }
                }, { view()?.showToastMessage(R.string.message_search_not_found) })
        )
    }
}