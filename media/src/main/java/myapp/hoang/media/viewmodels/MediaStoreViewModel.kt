package myapp.hoang.media.viewmodels

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
                    toggleMediaSelection(0)
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    fun toggleMediaSelection(toggledIndex: Int) {
        getBitmapFromUriJob?.cancel()

        getBitmapFromUriJob = viewModelScope.launch {
            try {
                // focusedIndex is always toggledIndex
                // unless the media at toggledIndex is unselected
                var focusedIndex = toggledIndex
                var shouldShowLimitAlert = false
                state = state.copy(
                    selectedMediaList = state.selectedMediaList.toMutableList()
                        .apply {
                            when (state.selectMediaMode) {
                                SelectMediaMode.SINGLE -> {
                                    clear()
                                    val selectedMedia = buildSelectedMedia(toggledIndex)
                                    add(selectedMedia)
                                }
                                SelectMediaMode.MULTIPLE -> {
                                    val alreadySelected = any { it.index == toggledIndex }
                                    if (alreadySelected && toggledIndex == state.focusedMediaIndex) {
                                        removeIf { it.index == toggledIndex }
                                        lastOrNull()?.let { focusedIndex = it.index }
                                    } else if (!alreadySelected && size < 10) {
                                        val selectedMedia = buildSelectedMedia(toggledIndex)
                                        add(selectedMedia)
                                    } else if (!alreadySelected) {
                                        shouldShowLimitAlert = true
                                    }
                                }
                            }
                            toList()
                        }
                )
                state = if (shouldShowLimitAlert) {
                    state.copy(
                        showLimitAlert = triggered
                    )
                } else {
                    state.copy(
                        focusedMediaIndex = focusedIndex
                    )
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    private suspend fun buildSelectedMedia(index: Int): SelectedMedia {
        return SelectedMedia(
            index = index,
            crop = false,
            originalBitmap = mediaSharedStorageRepository
                .getBitmapFromUri(state.mediaList[index].contentUri)
                .asImageBitmap(),
            croppedBitmap = null
        )
    }

    fun startCropping() {
        state = state.copy(
            selectedMediaList = state.selectedMediaList
                .map {
                    SelectedMedia(
                        index = it.index,
                        crop = true,
                        originalBitmap = it.originalBitmap,
                        croppedBitmap = it.croppedBitmap
                    )
                }
        )
        Log.d(TAG, "startCropping all selected media")
    }

    fun finishCropping(index: Int, croppedImageBitmap: ImageBitmap) {
        state = state.copy(
            selectedMediaList = state.selectedMediaList
                .map {
                    if (it.index == index)
                        SelectedMedia(
                            index = it.index,
                            crop = false,
                            originalBitmap = it.originalBitmap,
                            croppedBitmap = croppedImageBitmap
                        )
                    else it
                }
        )
        Log.d(TAG, "finishCropping media $index")
        // check if selected images are cropped
        if (state.selectedMediaList.all { it.croppedBitmap != null }) {
            state = state.copy(
                nextScreenEvent = triggered
            )
        }
    }

    fun uploadPostImageAndCreatePost() {
        state = state.copy(
            isLoading = true
        )
        uploadPostImageAndCreatePostJob?.cancel()
        uploadPostImageAndCreatePostJob = viewModelScope.launch {
            state = try {
                imageUploadRepository.uploadPostImages(
                    state.selectedMediaList.mapNotNull { it.croppedBitmap?.asAndroidBitmap() }
                )
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