package myapp.hoang.instaclone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.core.coroutines.DispatcherProviderImpl
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
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }
}