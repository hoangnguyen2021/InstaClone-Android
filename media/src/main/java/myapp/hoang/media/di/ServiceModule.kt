package myapp.hoang.media.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import myapp.hoang.media.services.AndroidMediaSharedStorageService
import myapp.hoang.media.services.ImageUploadService
import myapp.hoang.media.services.KtorImageUploadService
import myapp.hoang.media.services.MediaSharedStorageService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideMediaStoreService(@ApplicationContext context: Context): MediaSharedStorageService {
        return AndroidMediaSharedStorageService(context)
    }

    @Provides
    @Singleton
    fun provideImageUploadService(httpClient: HttpClient): ImageUploadService {
        return KtorImageUploadService(httpClient)
    }
}