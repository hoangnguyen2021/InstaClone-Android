package myapp.hoang.media.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import myapp.hoang.core.models.InstaCloneUser

@Serializable
data class ReplyComment(
    val _id: String,
    val author: InstaCloneUser,
    val content: String,
    val isEdited: Boolean = false,
    val createdAt: Instant,
    val lastEditedAt: Instant,
    val likes: List<String> = emptyList(),
    val tags: List<String> = emptyList()
)