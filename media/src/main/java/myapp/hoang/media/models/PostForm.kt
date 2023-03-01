package myapp.hoang.media.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class PostForm(
    val caption: String,
    val authorUsername: String,
    val createdAt: Instant = Clock.System.now(),
    val lastEditedAt: Instant = Clock.System.now(),
    val mediaPaths: List<String> = emptyList()
)