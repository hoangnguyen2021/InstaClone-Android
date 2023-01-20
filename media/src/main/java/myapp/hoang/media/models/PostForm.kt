package myapp.hoang.media.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class PostForm(
    val mediaList: List<Media>,
    val createdAt: Instant = Clock.System.now(),
    val caption: String
)
