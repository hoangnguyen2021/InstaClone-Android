package myapp.hoang.onboarding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.onboarding.login.datasources.LoginRemoteDataSource
import myapp.hoang.onboarding.login.datasources.LoginRemoteDataSourceImpl
import myapp.hoang.onboarding.login.services.LoginService
import myapp.hoang.onboarding.signup.datasources.SignupRemoteDataSource
import myapp.hoang.onboarding.signup.datasources.SignupRemoteDataSourceImpl
import myapp.hoang.onboarding.signup.services.SignupService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideSignupRemoteDataSource(
        signupService: SignupService,
        dispatcherProvider: DispatcherProvider
    ): SignupRemoteDataSource {
        return SignupRemoteDataSourceImpl(signupService, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideLoginRemoteDataSource(
        loginService: LoginService,
        dispatcherProvider: DispatcherProvider
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(loginService, dispatcherProvider)
    }
}