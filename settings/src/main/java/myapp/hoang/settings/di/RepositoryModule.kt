package myapp.hoang.settings.di

import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.settings.models.UserPreferences
import myapp.hoang.settings.repositories.UserPreferencesRepository
import myapp.hoang.settings.repositories.UserPreferencesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        userPreferencesDataStore: DataStore<UserPreferences>
    ): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(userPreferencesDataStore)
    }
}