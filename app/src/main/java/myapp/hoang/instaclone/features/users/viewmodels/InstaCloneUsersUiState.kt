package myapp.hoang.instaclone.features.users.viewmodels

import myapp.hoang.core.models.InstaCloneUser

data class InstaCloneUsersUiState(
    val user: InstaCloneUser? = null,
    val isLoading: Boolean = false
)