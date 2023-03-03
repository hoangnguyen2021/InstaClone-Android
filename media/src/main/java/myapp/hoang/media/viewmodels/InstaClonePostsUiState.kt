package myapp.hoang.media.viewmodels

import myapp.hoang.core.models.InstaCloneUser
import myapp.hoang.media.models.InstaClonePost

data class InstaClonePostsUiState(
    val posts: List<InstaClonePost> = emptyList(),
    val areLiked: List<Boolean> = emptyList(),
    val post: InstaClonePost? = null,
    val author: InstaCloneUser? = null,
    val commenters: List<InstaCloneUser> = emptyList(),
    val isLoading: Boolean = false
)