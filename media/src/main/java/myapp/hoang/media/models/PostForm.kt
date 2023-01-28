package myapp.hoang.media.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class PostForm(
    val caption: String? = null,
    val authorUsername: String? = null,
    val createdAt: Instant? = null,
    val lastEditedAt: Instant? = null,
    val mediaPaths: List<String> = emptyList()
)