package com.sarafaria.android.github.user.domain.usecase

import com.sarafaria.android.github.user.domain.UserRepository
import com.sarafaria.android.github.user.mock.UserMock
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verifyAll
import io.reactivex.Single
import net.andreinc.mockneat.MockNeat
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserUseCaseTest {

    @MockK
    lateinit var mRepository: UserRepository

    lateinit var SUT: UserUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = UserUseCaseImpl(mRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `get all user with success`() {
        // Arrange
        val userEntityList = arrayListOf(UserMock.userEntityMock)
        every { mRepository.getAllUsers() } answers {
            Single.just(userEntityList)
        }

        // Act
        val test = SUT.getAllUsers().test()

        // Assert
        test.assertNoErrors()
        test.assertComplete()

        verifyAll {
            mRepository.getAllUsers()
        }
    }

    @Test
    fun `get all user with error`() {
        // Arrange
        val throwable = Throwable()
        every { mRepository.getAllUsers() } answers {
            Single.error(throwable)
        }

        // Act
        val test = SUT.getAllUsers().test()

        // Assert
        test.assertNotComplete()
        test.onError(throwable)

        verifyAll {
            mRepository.getAllUsers()
        }
    }

    @Test
    fun `get user by username with success`() {
        // Arrange
        val userEntity = UserMock.userEntityMock
        val username = MockNeat.threadLocal().strings().get()
        every { mRepository.getUserByUsername(username) } answers {
            Single.just(userEntity)
        }

        // Act
        val test = SUT.getUserByUsername(username).test()

        // Assert
        test.assertNoErrors()
        test.assertComplete()

        verifyAll {
            mRepository.getUserByUsername(username)
        }
    }

    @Test
    fun `get user by username with error`() {
        // Arrange
        val throwable = Throwable()
        val username = MockNeat.threadLocal().strings().get()
        every { mRepository.getUserByUsername(username) } answers {
            Single.error(throwable)
        }

        // Act
        val test = SUT.getUserByUsername(username).test()

        // Assert
        test.assertNotComplete()
        test.onError(throwable)

        verifyAll {
            mRepository.getUserByUsername(username)
        }
    }
}