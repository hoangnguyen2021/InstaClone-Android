package myapp.hoang.onboarding.login.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String
)
