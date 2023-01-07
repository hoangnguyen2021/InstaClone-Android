package myapp.hoang.media.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.media.datasources.MediaStoreDaraSourceImpl
import myapp.hoang.media.datasources.MediaStoreDataSource
import myapp.hoang.media.services.MediaStoreService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideMediaStoreDataSource(
        mediaStoreService: MediaStoreService,
        dispatcherProvider: DispatcherProvider
    ): MediaStoreDataSource {
        return MediaStoreDaraSourceImpl(mediaStoreService, dispatcherProvider)
    }
}