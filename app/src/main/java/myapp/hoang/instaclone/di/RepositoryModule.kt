package myapp.hoang.instaclone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.onboarding.login.datasources.LoginRemoteDataSource
import myapp.hoang.onboarding.login.repositories.LoginRepository
import myapp.hoang.onboarding.login.repositories.LoginRepositoryImpl
import myapp.hoang.onboarding.signup.datasources.SignupRemoteDataSource
import myapp.hoang.media.repositories.ImageUploadRepository
import myapp.hoang.media.repositories.ImageUploadRepositoryImpl
import myapp.hoang.onboarding.signup.repositories.SignupRepository
import myapp.hoang.onboarding.signup.repositories.SignupRepositoryImpl
import myapp.hoang.media.services.ImageUploadService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideSignupRepository(signupRemoteDataSource: SignupRemoteDataSource): SignupRepository {
        return SignupRepositoryImpl(signupRemoteDataSource)
    }
    @Provides
    @Singleton
    fun provideLoginRepository(loginRemoteDataSource: LoginRemoteDataSource): LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideImageUploadRepository(
        imageUploadService: ImageUploadService,
        dispatcherProvider: DispatcherProvider
    ): ImageUploadRepository {
        return ImageUploadRepositoryImpl(imageUploadService, dispatcherProvider)
    }
}