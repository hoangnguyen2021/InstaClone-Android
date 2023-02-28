package myapp.hoang.media.viewmodels

import myapp.hoang.media.models.InstaClonePost

data class InstaClonePostsUiState(
    val posts: List<InstaClonePost> = emptyList(),
    val areLiked: List<Boolean> = emptyList(),
    val post: InstaClonePost? = null,
    val isLoading: Boolean = false
)