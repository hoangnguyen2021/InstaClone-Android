package myapp.hoang.instaclone

import java.time.LocalDate

data class SignupForm(
    var mobileNumber: Long? = null,
    var isMobileNumberVerified: Boolean = false,
    var email: String? = null,
    var isEmailVerified: Boolean = false,
    var fullName: String? = null,
    var birthday: LocalDate? = null,
    var username: String? = null,
    var agreedToPolicy: Boolean = false,
    var profilePicUrl: String? = null
)
