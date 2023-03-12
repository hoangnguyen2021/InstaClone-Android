package myapp.hoang.media.viewmodels

import myapp.hoang.core.models.InstaCloneUser
import myapp.hoang.media.models.InstaClonePost

data class InstaClonePostsUiState(
    val posts: List<InstaClonePost> = emptyList(),
    val arePostsLiked: List<Boolean> = emptyList(),
    val postsLikes: List<Int> = emptyList(),
    val post: InstaClonePost? = null,
    val author: InstaCloneUser? = null,
    val areCommentsLiked: List<Boolean> = emptyList(),
    val commentsLikes: List<Int> = emptyList(),
    val areReplyCommentsLiked: List<List<Boolean>> = emptyList(),
    val replyCommentsLikes: List<List<Int>> = emptyList(),
    val isLoading: Boolean = false
)