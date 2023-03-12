package myapp.hoang.media.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ReplyCommentForm(
    val authorId: String,
    val commentId: String,
    val content: String,
    val isEdited: Boolean = false,
    val createdAt: Instant = Clock.System.now(),
    val lastEditedAt: Instant = Clock.System.now(),
    val tags: List<String> = emptyList()
)