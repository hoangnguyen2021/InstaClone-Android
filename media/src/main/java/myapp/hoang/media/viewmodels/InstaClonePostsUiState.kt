package myapp.hoang.media.viewmodels

import myapp.hoang.media.models.InstaClonePost

data class InstaClonePostsUiState(
    val posts: List<InstaClonePost> = emptyList(),
    val isLoading: Boolean = false
)