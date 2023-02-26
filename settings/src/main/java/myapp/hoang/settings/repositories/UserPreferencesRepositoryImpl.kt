package myapp.hoang.settings.repositories

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import myapp.hoang.settings.models.UserPreferences
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
): UserPreferencesRepository {
    override suspend fun getUserPreferences(): Flow<UserPreferences> = userPreferences.data

    override suspend fun updateUserPreferences(userPreferences: UserPreferences) {
        this.userPreferences.updateData { userPreferences }
    }
}