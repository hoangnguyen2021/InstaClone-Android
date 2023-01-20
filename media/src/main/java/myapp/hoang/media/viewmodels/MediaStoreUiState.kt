package myapp.hoang.media.viewmodels

import androidx.compose.ui.graphics.ImageBitmap
import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed
import myapp.hoang.media.models.Media

data class MediaStoreUiState(
    val mediaList: List<Media> = emptyList(),
    val imageBitmap: ImageBitmap? = null,
    val crop: Boolean = false,
    val isCropping: Boolean = false,
    val editedImageBitmap: ImageBitmap? = null,
    val isLoading: Boolean = false,
    val nextScreenEvent: StateEvent = consumed
)