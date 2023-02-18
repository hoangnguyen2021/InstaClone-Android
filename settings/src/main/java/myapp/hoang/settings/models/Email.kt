package myapp.hoang.settings.models

import kotlinx.serialization.Serializable

@Serializable
data class Email(
    val _id: String,
    val email: String,
    val isVerified: Boolean = false
)