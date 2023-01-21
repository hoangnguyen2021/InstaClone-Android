package myapp.hoang.media.viewmodels

import androidx.compose.ui.graphics.ImageBitmap
import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.SelectMediaMode

data class MediaStoreUiState(
    val mediaList: List<Media> = emptyList(),
    val selectMediaMode: SelectMediaMode = SelectMediaMode.SINGLE,
    val selectedMediaSet: Set<SelectedMedia> = emptySet(),
    val focusedMedia: SelectedMedia = SelectedMedia(0),
    val cropPreviewBitmap: ImageBitmap? = null,
    val crop: Boolean = false,
    val isCropping: Boolean = false,
    val editedBitmaps: Set<ImageBitmap> = emptySet(),
    val isLoading: Boolean = false,
    val nextScreenEvent: StateEvent = consumed,
    val showLimitAlert: StateEvent = consumed
)