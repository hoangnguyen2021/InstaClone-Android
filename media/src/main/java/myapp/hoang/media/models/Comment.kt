package myapp.hoang.media.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val _id: String,
    val authorId: String,
    val content: String,
    val isEdited: Boolean = false,
    val createdAt: Instant,
    val lastEditedAt: Instant,
    val likes: Int = 0,
    val tags: List<String> = emptyList(),
    val replies: List<ReplyComment> = emptyList()
)