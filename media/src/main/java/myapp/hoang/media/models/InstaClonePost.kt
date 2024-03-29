package myapp.hoang.media.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import myapp.hoang.core.models.InstaCloneUser

@Serializable
data class InstaClonePost(
    val _id: String,
    val author: InstaCloneUser,
    val caption: String,
    val isEdited: Boolean = false,
    val createdAt: Instant,
    val lastEditedAt: Instant,
    val mediaPaths: List<String>,
    val likes: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val comments: List<Comment> = emptyList()
)