package myapp.hoang.instaclone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import myapp.hoang.instaclone.features.users.services.KtorUsersService
import myapp.hoang.instaclone.features.users.services.UsersService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideUsersService(httpClient: HttpClient): UsersService {
        return KtorUsersService(httpClient)
    }
}