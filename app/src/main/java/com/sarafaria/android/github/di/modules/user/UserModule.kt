package com.sarafaria.android.github.di.modules.user

import com.sarafaria.android.github.user.UserPresenter
import com.sarafaria.android.github.user.data.UserRepositoryImpl
import com.sarafaria.android.github.user.data.api.UserService
import com.sarafaria.android.github.user.data.datasource.UserCloudDataSource
import com.sarafaria.android.github.user.data.datasource.UserDataSource
import com.sarafaria.android.github.user.domain.UserRepository
import com.sarafaria.android.github.user.domain.usecase.UserUseCase
import com.sarafaria.android.github.user.domain.usecase.UserUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UserModule {

    @Provides
    fun providePresenter(useCase: UserUseCase) =
            UserPresenter(useCase)

    @Provides
    fun provideUseCase(repository: UserRepository): UserUseCase {
        return UserUseCaseImpl(repository)
    }

    @Provides
    fun provideRepository(cloudDataSource: UserDataSource): UserRepository {
        return UserRepositoryImpl(cloudDataSource)
    }

    @Provides
    fun providesCloudDataSource(service: UserService): UserDataSource {
        return UserCloudDataSource(service)
    }

    @Provides
    fun provideSellerProfileService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}