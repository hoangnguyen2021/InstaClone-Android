package myapp.hoang.instaclone

import java.time.LocalDate

data class InstaCloneUser(
    val uid: String,
    val mobileNumber: MobileNumber?,
    val email: Email?,
    val fullName: String,
    val birthday: LocalDate,
    val username: String,
    val agreedToPolicy: Boolean,
    val profilePicUrl: String?
)
