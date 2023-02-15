package myapp.hoang.media.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class InstaClonePost(
    val _id: String,
    val authorId: String,
    val caption: String,
    val createdAt: Instant,
    val lastEditedAt: Instant,
    val mediaPaths: List<String>
)