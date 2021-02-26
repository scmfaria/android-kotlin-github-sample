package com.sarafaria.android.github.user.data

import com.sarafaria.android.github.user.data.datasource.UserDataSource
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

class UserRepositoryTest {

    @MockK
    lateinit var mDataSource: UserDataSource

    lateinit var SUT: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = UserRepositoryImpl(mDataSource)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `get all user with success`() {
        // Arrange
        val userEntityList = arrayListOf(UserMock.userEntityMock)
        every { mDataSource.getAllUsers() } answers {
            Single.just(userEntityList)
        }

        // Act
        val test = SUT.getAllUsers().test()

        // Assert
        test.assertNoErrors()
        test.assertComplete()

        verifyAll {
            mDataSource.getAllUsers()
        }
    }

    @Test
    fun `get all user with error`() {
        // Arrange
        val throwable = Throwable()
        every { mDataSource.getAllUsers() } answers {
            Single.error(throwable)
        }

        // Act
        val test = SUT.getAllUsers().test()

        // Assert
        test.assertNotComplete()
        test.onError(throwable)

        verifyAll {
            mDataSource.getAllUsers()
        }
    }

    @Test
    fun `get user by username with success`() {
        // Arrange
        val userEntity = UserMock.userEntityMock
        val username = MockNeat.threadLocal().strings().get()
        every { mDataSource.getUserByUsername(username) } answers {
            Single.just(userEntity)
        }

        // Act
        val test = SUT.getUserByUsername(username).test()

        // Assert
        test.assertNoErrors()
        test.assertComplete()

        verifyAll {
            mDataSource.getUserByUsername(username)
        }
    }

    @Test
    fun `get user by username with error`() {
        // Arrange
        val throwable = Throwable()
        val username = MockNeat.threadLocal().strings().get()
        every { mDataSource.getUserByUsername(username) } answers {
            Single.error(throwable)
        }

        // Act
        val test = SUT.getUserByUsername(username).test()

        // Assert
        test.assertNotComplete()
        test.onError(throwable)

        verifyAll {
            mDataSource.getUserByUsername(username)
        }
    }
}