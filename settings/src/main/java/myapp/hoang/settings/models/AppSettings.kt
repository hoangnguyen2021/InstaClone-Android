package myapp.hoang.settings.models

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val username: String? = null,
    val authToken: String? = null
)
