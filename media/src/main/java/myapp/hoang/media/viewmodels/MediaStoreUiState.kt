package myapp.hoang.media.viewmodels

import myapp.hoang.media.models.Media

data class MediaStoreUiState(
    val mediaList: List<Media> = emptyList()
)
