package com.sarafaria.android.github.user

import com.sarafaria.android.github.R
import com.sarafaria.android.github.user.domain.usecase.UserUseCase
import com.sarafaria.android.github.user.mock.UserMock
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import net.andreinc.mockneat.MockNeat
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserPresenterTest {

    @MockK(relaxed = true)
    lateinit var mView: UserContract.View

    @MockK
    lateinit var mUseCase: UserUseCase

    lateinit var SUT: UserPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        SUT = spyk(UserPresenter(mUseCase), recordPrivateCalls = true)
        SUT.bind(mView)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `search users with success`() {
        // Arrange
        val users = arrayListOf(UserMock.userMock)
        every { mUseCase.getAllUsers() } answers {
            Single.just(users)
        }

        // Act
        SUT.searchUsers()

        // Assert
        verifyAll {
            mView.showContainerMessage()
            mView.showMessage(R.string.message_wait_process)
            mView.dismissContainerMessage()
            mView.showRecyclerView()
            mView.loadUsers(users)
            mView.dismissProgressbar()
        }
    }

    @Test
    fun `search users with throwable - error message`() {
        // Arrange
        val errorMessage = "errorMessage"
        val throwable = Throwable(errorMessage)
        every { mUseCase.getAllUsers() } answers {
            Single.error(throwable)
        }

        // Act
        SUT.searchUsers()

        // Assert
        verifyAll {
            mView.showContainerMessage()
            mView.showMessage(R.string.message_wait_process)
            mView.showMessage(errorMessage)
            mView.dismissProgressbar()
        }
    }

    @Test
    fun `search users with throwable - not exists error message`() {
        // Arrange
        val throwable = Throwable()
        every { mUseCase.getAllUsers() } answers {
            Single.error(throwable)
        }

        // Act
        SUT.searchUsers()

        // Assert
        verifyAll {
            mView.showContainerMessage()
            mView.showMessage(R.string.message_wait_process)
            mView.showMessage(R.string.message_error_search_users)
            mView.dismissProgressbar()
        }
    }

    @Test
    fun `search user by username with error`() {
        // Arrange
        val username = MockNeat.threadLocal().strings().get()
        val throwable = Throwable()
        every { mUseCase.getUserByUsername(username) } answers {
            Single.error(throwable)
        }

        // Act
        SUT.searchUserByUsername(username)

        // Assert
        verifyAll {
            mView.showToastMessage(R.string.message_search_not_found)
            mView.dismissProgressbar()
        }
    }

    @Test
    fun `search user by username with success`() {
        // Arrange
        val username = MockNeat.threadLocal().strings().get()
        val user = UserMock.userMock
        every { mUseCase.getUserByUsername(username) } answers {
            Single.just(user)
        }

        // Act
        SUT.searchUserByUsername(username)

        // Assert
        verifyAll {
            mView.showRecyclerView()
            mView.loadUsers(arrayListOf(user))
            mView.dismissProgressbar()
        }
    }
}