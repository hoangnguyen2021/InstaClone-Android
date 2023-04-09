package myapp.hoang.settings.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import myapp.hoang.core.models.Email
import myapp.hoang.core.models.MobileNumber

@Serializable
data class UserPreferences(
    val id: String? = null,
    val username: String? = null,
    val authToken: String? = null,
    val mobileNumber: MobileNumber? = null,
    val email: Email? = null,
    val fullName: String? = null,
    val birthday: LocalDate? = null,
    val agreedToPolicy: Boolean? = null,
    val profilePicPath: String? = null,
    val followers: List<String> = emptyList(),
    val following: List<String> = emptyList()
)