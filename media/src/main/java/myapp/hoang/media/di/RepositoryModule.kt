package myapp.hoang.media.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.media.datasources.MediaStoreDataSource
import myapp.hoang.media.repositories.MediaStoreRepository
import myapp.hoang.media.repositories.MediaStoreRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMediaStoreRepository(
        mediaStoreDataSource: MediaStoreDataSource
    ): MediaStoreRepository {
        return MediaStoreRepositoryImpl(mediaStoreDataSource)
    }
}