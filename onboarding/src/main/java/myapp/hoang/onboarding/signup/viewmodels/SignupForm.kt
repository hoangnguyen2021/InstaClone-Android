package myapp.hoang.onboarding.signup.viewmodels

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class SignupForm(
    var mobileNumber: Long? = null,
    var isMobileNumberVerified: Boolean = false,
    var email: String? = null,
    var isEmailVerified: Boolean = false,
    var fullName: String? = null,
    var birthday: LocalDate? = null,
    var username: String? = null,
    var password: String? = null,
    var agreedToPolicy: Boolean = false,
    var profilePicPath: String? = null
)
