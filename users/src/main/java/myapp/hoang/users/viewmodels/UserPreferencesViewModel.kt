package myapp.hoang.users.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import myapp.hoang.settings.repositories.UserPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class UserPreferencesViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UserPreferencesUiState())
    val uiState = _uiState.asStateFlow()

    private var state: UserPreferencesUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var getUserPreferencesJob: Job? = null

    fun getUserPreferences() {
        getUserPreferencesJob?.cancel()

        getUserPreferencesJob = viewModelScope.launch {
            userPreferencesRepository.getUserPreferences().collect {
                state = state.copy(
                    userPreferences = it
                )
            }
        }
    }
}