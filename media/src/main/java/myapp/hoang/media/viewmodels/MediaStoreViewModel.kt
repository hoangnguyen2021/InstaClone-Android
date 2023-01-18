package myapp.hoang.media.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import myapp.hoang.media.repositories.MediaStoreRepository
import javax.inject.Inject

@HiltViewModel
class MediaStoreViewModel @Inject constructor(
    private val mediaStoreRepository: MediaStoreRepository
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
                val mediaList = mediaStoreRepository.getAllMedia()
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
                val bitmap = mediaStoreRepository.getBitmapFromUri(uri)
                state = state.copy(
                    imageBitmap = bitmap.asImageBitmap()
                )
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    fun setCrop(crop: Boolean) {
        state = state.copy(
            crop = crop
        )
        Log.d(TAG, "setCrop")
    }

    fun setCroppedImageBitmap(croppedImageBitmap: ImageBitmap) {
        state = state.copy(
            croppedImageBitmap = croppedImageBitmap
        )
        Log.d(TAG, "setCroppedImageBitmap")
    }

    companion object {
        private val TAG = MediaStoreViewModel::class.java.simpleName
    }
}