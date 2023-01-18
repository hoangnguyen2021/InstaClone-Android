package myapp.hoang.media.viewmodels

import androidx.compose.ui.graphics.ImageBitmap
import myapp.hoang.media.models.Media

data class MediaStoreUiState(
    val mediaList: List<Media> = emptyList(),
    val imageBitmap: ImageBitmap? = null,
    val crop: Boolean = false,
    val croppedImageBitmap: ImageBitmap? = null
)