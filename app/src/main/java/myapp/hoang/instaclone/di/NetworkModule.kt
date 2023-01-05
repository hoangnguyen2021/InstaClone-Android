package myapp.hoang.instaclone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import myapp.hoang.core.config.NetworkConfig.HOST
import myapp.hoang.onboarding.login.services.KtorLoginService
import myapp.hoang.onboarding.login.services.LoginService
import myapp.hoang.onboarding.signup.services.ImageUploadService
import myapp.hoang.onboarding.signup.services.KtorImageUploadService
import myapp.hoang.onboarding.signup.services.KtorSignupService
import myapp.hoang.onboarding.signup.services.SignupService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTP
                    host = HOST
                    port = 8080
                }
            }
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
            expectSuccess = true
        }
    }

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

    @Provides
    @Singleton
    fun provideImageUploadService(httpClient: HttpClient): ImageUploadService {
        return KtorImageUploadService(httpClient)
    }
}