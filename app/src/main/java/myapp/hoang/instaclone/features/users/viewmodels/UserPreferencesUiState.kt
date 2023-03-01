package myapp.hoang.instaclone.features.users.viewmodels

import myapp.hoang.settings.models.UserPreferences

data class UserPreferencesUiState(
    val userPreferences: UserPreferences = UserPreferences()
)
