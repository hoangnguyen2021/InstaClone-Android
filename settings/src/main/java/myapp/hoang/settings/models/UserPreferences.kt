package myapp.hoang.settings.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val username: String? = null,
    val authToken: String? = null,
    val mobileNumber: MobileNumber? = null,
    val email: Email? = null,
    val fullName: String? = null,
    val birthday: LocalDate? = null,
    val agreedToPolicy: Boolean? = null,
    val profilePicPath: String? = null
)
