package myapp.hoang.media.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
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
import myapp.hoang.media.repositories.MediaSharedStorageRepository
import javax.inject.Inject

@HiltViewModel
class MediaStoreViewModel @Inject constructor(
    private val mediaSharedStorageRepository: MediaSharedStorageRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(MediaStoreUiState())
    val uiState = _uiState.asStateFlow()

    private var state: MediaStoreUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var getAllMediaJob: Job? = null
    private var getBitmapFromUriJob: Job? = null

    fun getAllMedia() {
        getAllMediaJob?.cancel()

        getAllMediaJob = viewModelScope.launch {
            try {
                val mediaList = mediaSharedStorageRepository.getAllMedia()
                state = state.copy(
                    mediaList = mediaList
                )
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    fun getBitmapFromUri(uri: Uri) {
        getBitmapFromUriJob?.cancel()

        getBitmapFromUriJob = viewModelScope.launch {
            try {
                val bitmap = mediaSharedStorageRepository.getBitmapFromUri(uri)
                state = state.copy(
                    imageBitmap = bitmap.asImageBitmap()
                )
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
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
            croppedImageBitmap = croppedImageBitmap,
            nextScreenEvent = triggered
        )
        Log.d(TAG, "finishCropping")
    }

    fun onConsumedNextScreenEvent() {
        state = state.copy(nextScreenEvent = consumed)
    }

    companion object {
        private val TAG = MediaStoreViewModel::class.java.simpleName
    }
}