package myapp.hoang.media.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import myapp.hoang.media.services.AndroidMediaStoreService
import myapp.hoang.media.services.MediaStoreService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideMediaStoreService(@ApplicationContext context: Context): MediaStoreService {
        return AndroidMediaStoreService(context)
    }
}