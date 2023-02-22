package myapp.hoang.core.models

import kotlinx.serialization.Serializable

@Serializable
data class MobileNumber(
    val _id: String,
    val number: Long,
    val isVerified: Boolean = false
)