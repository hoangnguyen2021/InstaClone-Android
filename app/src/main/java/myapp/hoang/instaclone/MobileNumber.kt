package myapp.hoang.instaclone

data class MobileNumber(
    var id: String,
    var number: Long,
    var isVerified: Boolean = false
)
