package myapp.hoang.media.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ReplyComment(
    val _id: String,
    val authorId: String,
    val content: String,
    val createdAt: Instant,
    val lastEditedAt: Instant,
    val likes: Int = 0,
    val tags: List<String>
)