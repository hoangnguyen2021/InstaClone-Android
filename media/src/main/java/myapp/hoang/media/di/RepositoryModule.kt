package myapp.hoang.media.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.media.datasources.MediaSharedStorageDataSource
import myapp.hoang.media.repositories.ImageUploadRepository
import myapp.hoang.media.repositories.ImageUploadRepositoryImpl
import myapp.hoang.media.repositories.MediaSharedStorageRepository
import myapp.hoang.media.repositories.MediaSharedStorageRepositoryImpl
import myapp.hoang.media.services.ImageUploadService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMediaStoreRepository(
        mediaSharedStorageDataSource: MediaSharedStorageDataSource
    ): MediaSharedStorageRepository {
        return MediaSharedStorageRepositoryImpl(mediaSharedStorageDataSource)
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