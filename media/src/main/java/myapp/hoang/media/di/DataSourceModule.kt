package myapp.hoang.media.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.media.datasources.MediaSharedStorageDataSourceImpl
import myapp.hoang.media.datasources.MediaSharedStorageDataSource
import myapp.hoang.media.datasources.PostRemoteDataSource
import myapp.hoang.media.datasources.PostRemoteDataSourceImpl
import myapp.hoang.media.services.MediaSharedStorageService
import myapp.hoang.media.services.PostService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideMediaStoreDataSource(
        mediaSharedStorageService: MediaSharedStorageService,
        dispatcherProvider: DispatcherProvider
    ): MediaSharedStorageDataSource {
        return MediaSharedStorageDataSourceImpl(mediaSharedStorageService, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun providePostRemoteDataSource(postService: PostService): PostRemoteDataSource {
        return PostRemoteDataSourceImpl(postService)
    }
}