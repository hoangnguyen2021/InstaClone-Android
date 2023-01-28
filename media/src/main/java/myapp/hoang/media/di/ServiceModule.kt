package myapp.hoang.media.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import myapp.hoang.media.services.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideMediaStoreService(@ApplicationContext appContext: Context): MediaSharedStorageService {
        return AndroidMediaSharedStorageService(appContext)
    }

    @Provides
    @Singleton
    fun provideImageUploadService(httpClient: HttpClient): ImageUploadService {
        return KtorImageUploadService(httpClient)
    }

    @Provides
    @Singleton
    fun providePostService(httpClient: HttpClient): PostService {
        return KtorPostService(httpClient)
    }
}