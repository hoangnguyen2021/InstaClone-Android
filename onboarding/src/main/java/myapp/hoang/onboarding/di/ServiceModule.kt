package myapp.hoang.onboarding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import myapp.hoang.onboarding.login.services.KtorLoginService
import myapp.hoang.onboarding.login.services.LoginService
import myapp.hoang.onboarding.signup.services.KtorSignupService
import myapp.hoang.onboarding.signup.services.SignupService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideSignupService(httpClient: HttpClient): SignupService {
        return KtorSignupService(httpClient)
    }

    @Provides
    @Singleton
    fun provideLoginService(httpClient: HttpClient): LoginService {
        return KtorLoginService(httpClient)
    }
}