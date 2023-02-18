package myapp.hoang.instaclone.features.users.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import myapp.hoang.instaclone.features.users.repositories.UsersRepository
import javax.inject.Inject

@HiltViewModel
class InstaCloneUsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(InstaCloneUsersUiState())
    val uiState = _uiState.asStateFlow()

    private var state: InstaCloneUsersUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var getUserByIdJob: Job? = null

    fun getUserById(id: String) {
        getUserByIdJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        getUserByIdJob = viewModelScope.launch {
            try {
                val user = usersRepository.getUserById(id)
                state = state.copy(
                    user = user,
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    companion object {
        private val TAG = InstaCloneUsersViewModel::class.java.simpleName
    }
}