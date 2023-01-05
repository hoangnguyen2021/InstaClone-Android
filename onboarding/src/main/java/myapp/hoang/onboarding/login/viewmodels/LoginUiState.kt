package myapp.hoang.onboarding.login.viewmodels

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed
import myapp.hoang.onboarding.login.models.AuthResponse
import myapp.hoang.onboarding.login.models.LoginForm

data class LoginUiState(
    val loginForm: LoginForm = LoginForm(),
    val isLoading: Boolean = false,
    val authResponse: AuthResponse? = null,
    val nextScreenEvent: StateEvent = consumed
)