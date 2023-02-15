package myapp.hoang.core.config

object NetworkConfig {
    const val HOST = "192.168.86.248"
    private const val ROUTE_AUTH = "auth"
    const val ROUTE_SEND_VERIFICATION_CODE = "$ROUTE_AUTH/send-verification-code"
    const val ROUTE_CHECK_VERIFICATION_CODE = "$ROUTE_AUTH/check-verification-code"
    const val ROUTE_SIGN_UP = "$ROUTE_AUTH/sign-up"
    const val ROUTE_LOG_IN = "$ROUTE_AUTH/log-in"

    private const val ROUTE_MEDIA = "media"
    private const val ROUTE_PROFILE_PIC = "profile-pic"
    private const val ROUTE_POST = "post"
    const val ROUTE_UPLOAD_PROFILE_PIC = "$ROUTE_MEDIA/$ROUTE_PROFILE_PIC/upload-profile-pic"
    const val ROUTE_GET_PROFILE_PIC = "$ROUTE_MEDIA/$ROUTE_PROFILE_PIC/get-profile-pic"
    const val ROUTE_UPLOAD_IMAGES = "$ROUTE_MEDIA/$ROUTE_POST/upload-images"
    const val ROUTE_CREATE_POST = "$ROUTE_MEDIA/$ROUTE_POST/create"
    const val ROUTE_GET_POSTS_BY_USER = "$ROUTE_MEDIA/$ROUTE_POST/posts-by-user"
}