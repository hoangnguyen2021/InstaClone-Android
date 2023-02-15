package myapp.hoang.media.viewmodels

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import myapp.hoang.media.repositories.PostRepository
import myapp.hoang.settings.models.UserPreferences
import javax.inject.Inject

@HiltViewModel
class InstaClonePostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userPreferences: DataStore<UserPreferences>
): ViewModel() {
    private val _uiState = MutableStateFlow(InstaClonePostsUiState())
    val uiState = _uiState.asStateFlow()

    private var state: InstaClonePostsUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var getAllPostsByUserJob: Job? = null

    fun getAllPostsByUser() {
        Log.d(TAG, "getAllPostsByUser")
        getAllPostsByUserJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        getAllPostsByUserJob = viewModelScope.launch {
            try {
                val authorUsername = userPreferences.data.first().username
                if (authorUsername == null) {
                    state = state.copy(
                        isLoading = true
                    )
                } else {
                    val posts = postRepository.getPostsByUser(authorUsername)
                    state = state.copy(
                        posts = posts,
                        isLoading = false
                    )
                    Log.d(TAG, posts.toString())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    companion object {
        private val TAG = InstaClonePostsViewModel::class.java.simpleName
    }
}