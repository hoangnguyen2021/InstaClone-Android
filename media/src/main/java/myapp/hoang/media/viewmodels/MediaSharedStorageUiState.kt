package myapp.hoang.media.viewmodels

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed
import myapp.hoang.media.models.ImageFilter
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.SelectMediaMode

data class MediaSharedStorageUiState(
    val mediaList: List<Media> = emptyList(),
    val focusedMediaIndex: Int = -1,
    val selectMediaMode: SelectMediaMode = SelectMediaMode.SINGLE,
    val selectedMediaList: List<SelectedMedia> = emptyList(),
    val imageFilterList: List<ImageFilter> = emptyList(),
    val isLoading: Boolean = false,
    val nextScreenEvent: StateEvent = consumed,
    val showLimitAlert: StateEvent = consumed
)