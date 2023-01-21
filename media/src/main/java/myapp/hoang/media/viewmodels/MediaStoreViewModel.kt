package myapp.hoang.media.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import myapp.hoang.media.models.SelectMediaMode
import myapp.hoang.media.repositories.ImageUploadRepository
import myapp.hoang.media.repositories.MediaSharedStorageRepository
import javax.inject.Inject

@HiltViewModel
class MediaStoreViewModel @Inject constructor(
    private val mediaSharedStorageRepository: MediaSharedStorageRepository,
    private val imageUploadRepository: ImageUploadRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MediaStoreUiState())
    val uiState = _uiState.asStateFlow()

    private var state: MediaStoreUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var getAllMediaJob: Job? = null
    private var getBitmapFromUriJob: Job? = null
    private var uploadPostImageAndCreatePostJob: Job? = null

    fun getAllMedia() {
        getAllMediaJob?.cancel()

        getAllMediaJob = viewModelScope.launch {
            try {
                val mediaList = mediaSharedStorageRepository.getAllMedia()
                state = state.copy(
                    mediaList = mediaList
                )
                // auto-select first media after load
                if (mediaList.isNotEmpty()) {
                    state = state.copy(
                        selectedMediaSet = setOf(SelectedMedia(0)),
                        focusedMedia = SelectedMedia(0)
                    )
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    fun setCropPreviewFromUri(uri: Uri) {
        getBitmapFromUriJob?.cancel()

        getBitmapFromUriJob = viewModelScope.launch {
            try {
                val bitmap = mediaSharedStorageRepository.getBitmapFromUri(uri)
                state = state.copy(
                    cropPreviewBitmap = bitmap.asImageBitmap()
                )
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    fun toggleMediaSelection(toggledMedia: SelectedMedia) {
        // focusedMediaIndex is always index unless
        // the media at this index is unselected
        var focusedMediaIndex = toggledMedia
        var shouldShowLimitAlert = false
        state = state.copy(
            selectedMediaSet = state.selectedMediaSet.toMutableSet()
                .apply {
                    when (state.selectMediaMode) {
                        SelectMediaMode.SINGLE -> {
                            clear()
                            add(toggledMedia)
                        }
                        SelectMediaMode.MULTIPLE -> {
                            if (this.contains(toggledMedia) && toggledMedia == state.focusedMedia) {
                                remove(toggledMedia)
                                this.lastOrNull()?.let { focusedMediaIndex = it }
                            } else if (size < 10) {
                                add(toggledMedia)
                            } else if (!this.contains(toggledMedia)) {
                                shouldShowLimitAlert = true
                            }
                        }
                    }
                    toSet()
                }
        )
        state = if (shouldShowLimitAlert) {
            state.copy(
                showLimitAlert = triggered
            )
        } else {
            state.copy(
                focusedMedia = focusedMediaIndex
            )
        }
    }

    fun startCropping() {
        state = state.copy(
            crop = true,
            isCropping = true
        )
        Log.d(TAG, "startCropping")
    }

    fun finishCropping(croppedImageBitmap: ImageBitmap) {
        state = state.copy(
            crop = false,
            isCropping = false,
            editedBitmaps = setOf(croppedImageBitmap),
            nextScreenEvent = triggered
        )
        Log.d(TAG, "finishCropping")
    }

    fun uploadPostImageAndCreatePost() {
        state = state.copy(
            isLoading = true
        )
        uploadPostImageAndCreatePostJob?.cancel()
        uploadPostImageAndCreatePostJob = viewModelScope.launch {
            state = try {
                val postImagePath = imageUploadRepository
                    .uploadPostImages(state.editedBitmaps.map { it.asAndroidBitmap() })
                state.copy(
                    isLoading = false,
                    nextScreenEvent = triggered
                )
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun toggleSelectMediaMode() {
        state = if (state.selectMediaMode == SelectMediaMode.SINGLE) {
            state.copy(
                selectMediaMode = SelectMediaMode.MULTIPLE
            )
        } else {
            state.copy(
                selectMediaMode = SelectMediaMode.SINGLE
            )
        }
    }

    fun onConsumedNextScreenEvent() {
        state = state.copy(nextScreenEvent = consumed)
    }

    fun onConsumedShowLimitAlert() {
        state = state.copy(showLimitAlert = consumed)
    }

    companion object {
        private val TAG = MediaStoreViewModel::class.java.simpleName
    }
}