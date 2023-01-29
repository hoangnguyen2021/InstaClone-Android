package myapp.hoang.media.viewmodels

import androidx.compose.ui.graphics.ImageBitmap

data class SelectedMedia(
    val index: Int,
    val crop: Boolean = false,
    val originalBitmap: ImageBitmap? = null,
    val croppedBitmap: ImageBitmap? = null,
    val filteredBitmap: ImageBitmap? = null
)