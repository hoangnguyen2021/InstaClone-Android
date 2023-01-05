package myapp.hoang.onboarding.login.viewmodels

import android.util.Log
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
import myapp.hoang.core.utils.Validator
import myapp.hoang.onboarding.login.models.LoginForm
import myapp.hoang.onboarding.login.repositories.LoginRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private var state: LoginUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var loginJob: Job? = null

    fun logIn(user: String, password: String) {
        state = state.copy(
            isLoading = true
        )

        val loginForm = if (Validator.validateEmailAddress(user)) {
            LoginForm(email = user, password = password)
        } else if (Validator.validateMobileNumber(user)) {
            LoginForm(mobileNumber = user.toLong(), password = password)
        } else if (user.isNotEmpty() && password.isNotEmpty()) {
            LoginForm(username = user, password = password)
        } else {
            state = state.copy(
                isLoading = false
            )
            return
        }

        loginJob?.cancel()
        loginJob = viewModelScope.launch {
            state = try {
                val authResponse = loginRepository.logIn(loginForm)
                state.copy(
                    isLoading = false,
                    authResponse = authResponse,
                    nextScreenEvent = triggered
                )
            } catch (e: Exception) {
                Log.d("MYTAG", e.toString())
                state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun onConsumedNextScreenEvent(){
        state = state.copy(nextScreenEvent = consumed)
    }
}