package myapp.hoang.media.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.media.datasources.MediaSharedStorageDataSource
import myapp.hoang.media.repositories.MediaSharedStorageRepository
import myapp.hoang.media.repositories.MediaSharedStorageRepositoryImpl
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
}