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
import kotlinx.datetime.Clock
import myapp.hoang.media.models.PostForm
import myapp.hoang.media.models.SelectMediaMode
import myapp.hoang.media.repositories.ImageFilterRepository
import myapp.hoang.media.repositories.ImageUploadRepository
import myapp.hoang.media.repositories.MediaSharedStorageRepository
import myapp.hoang.media.repositories.PostRepository
import myapp.hoang.settings.repositories.UserPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class MediaSharedStorageViewModel @Inject constructor(
    private val mediaSharedStorageRepository: MediaSharedStorageRepository,
    private val imageFilterRepository: ImageFilterRepository,
    private val imageUploadRepository: ImageUploadRepository,
    private val postRepository: PostRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MediaSharedStorageUiState())
    val uiState = _uiState.asStateFlow()

    private var state: MediaSharedStorageUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var getAllMediaJob: Job? = null
    private var getBitmapFromUriJob: Job? = null
    private var getAllImageFiltersJob: Job? = null
    private var uploadPostImageAndCreatePostJob: Job? = null

    fun getAllMedia() {
        getAllMediaJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        getAllMediaJob = viewModelScope.launch {
            try {
                val mediaList = mediaSharedStorageRepository.getAllMedia()
                state = state.copy(
                    mediaList = mediaList,
                    isLoading = false
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
            filterIndex = 0,
            originalBitmap = mediaSharedStorageRepository
                .getBitmapFromUri(state.mediaList[index].contentUri)
                .asImageBitmap(),
            croppedBitmap = null
        )
    }

    fun startCropping() {
        state = state.copy(
            selectedMediaList = state.selectedMediaList.map { it.copy(crop = true) }
        )
        Log.d(TAG, "startCropping all selected media")
    }

    fun finishCropping(index: Int, croppedImageBitmap: ImageBitmap) {
        state = state.copy(
            selectedMediaList = state.selectedMediaList
                .map {
                    if (it.index == index)
                        it.copy(
                            crop = false,
                            croppedBitmap = croppedImageBitmap,
                            filteredBitmap = croppedImageBitmap
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

    fun getAllImageFilters(unfilteredBitmap: ImageBitmap) {
        getAllImageFiltersJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        getAllImageFiltersJob = viewModelScope.launch {
            val imageFilterList =
                imageFilterRepository.getAllImageFilters(unfilteredBitmap.asAndroidBitmap())
            state = state.copy(
                imageFilterList = imageFilterList,
                isLoading = false
            )
        }
    }

    fun selectImageFilter(index: Int, filterIndex: Int) {
        state = state.copy(
            selectedMediaList = state.selectedMediaList
                .map {
                    if (index == it.index)
                        it.copy(filterIndex = filterIndex)
                    else it
                }
        )
    }

    fun resetImageFilter(index: Int) {
        selectImageFilter(index, 0)
    }

    fun applyImageFilter(index: Int, filteredBitmap: ImageBitmap?) {
        if (filteredBitmap != null) {
            state = state.copy(
                selectedMediaList = state.selectedMediaList
                    .map {
                        if (index == it.index)
                            it.copy(filteredBitmap = filteredBitmap)
                        else it
                    }
            )
        }
    }

    fun uploadPostImagesAndCreatePost(caption: String) {
        state = state.copy(
            isLoading = true
        )
        uploadPostImageAndCreatePostJob?.cancel()
        uploadPostImageAndCreatePostJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val mediaPaths = imageUploadRepository.uploadPostImages(
                        state.selectedMediaList.mapNotNull { it.filteredBitmap?.asAndroidBitmap() }
                    )
                    val postForm = PostForm(
                        caption = caption,
                        authorUsername = userPreferences.username,
                        createdAt = Clock.System.now(),
                        lastEditedAt = Clock.System.now(),
                        mediaPaths = mediaPaths
                    )
                    postRepository.createPost(postForm)
                    state = state.copy(
                        isLoading = false,
                        nextScreenEvent = triggered
                    )
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun onConsumedNextScreenEvent() {
        state = state.copy(nextScreenEvent = consumed)
    }

    fun onConsumedShowLimitAlert() {
        state = state.copy(showLimitAlert = consumed)
    }

    companion object {
        private val TAG = MediaSharedStorageViewModel::class.java.simpleName
    }
}