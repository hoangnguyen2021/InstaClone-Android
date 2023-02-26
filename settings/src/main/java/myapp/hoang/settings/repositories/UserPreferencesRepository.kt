package myapp.hoang.settings.repositories

import kotlinx.coroutines.flow.Flow
import myapp.hoang.settings.models.UserPreferences

interface UserPreferencesRepository {
    suspend fun getUserPreferences(): Flow<UserPreferences>
    suspend fun updateUserPreferences(userPreferences: UserPreferences)
}