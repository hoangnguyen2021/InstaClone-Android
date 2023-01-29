package myapp.hoang.onboarding.login.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import myapp.hoang.settings.models.Email
import myapp.hoang.settings.models.MobileNumber

@Serializable
data class AuthResponse(
    val token: String,
    val username: String,
    val mobileNumber: MobileNumber? = null,
    val email: Email? = null,
    val fullName: String,
    val birthday: LocalDate,
    val agreedToPolicy: Boolean,
    val profilePicPath: String? = null
)
