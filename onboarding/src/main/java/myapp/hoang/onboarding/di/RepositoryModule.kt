package myapp.hoang.onboarding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.onboarding.login.datasources.LoginRemoteDataSource
import myapp.hoang.onboarding.login.repositories.LoginRepository
import myapp.hoang.onboarding.login.repositories.LoginRepositoryImpl
import myapp.hoang.onboarding.signup.datasources.SignupRemoteDataSource
import myapp.hoang.onboarding.signup.repositories.SignupRepository
import myapp.hoang.onboarding.signup.repositories.SignupRepositoryImpl
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
}