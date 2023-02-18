package myapp.hoang.instaclone.features.users.viewmodels

import myapp.hoang.instaclone.models.InstaCloneUser

data class InstaCloneUsersUiState(
    val user: InstaCloneUser? = null,
    val isLoading: Boolean = false
)