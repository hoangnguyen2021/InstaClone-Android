package myapp.hoang.media.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import myapp.hoang.media.services.AndroidMediaSharedStorageService
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
}