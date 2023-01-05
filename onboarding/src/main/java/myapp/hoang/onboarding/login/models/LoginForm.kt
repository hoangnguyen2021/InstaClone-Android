package myapp.hoang.onboarding.login.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginForm(
    val mobileNumber: Long? = null,
    val email: String? = null,
    val username: String? = null,
    val password: String? = null
)