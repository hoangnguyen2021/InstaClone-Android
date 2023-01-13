package myapp.hoang.core.config

object NetworkConfig {
    const val HOST = "192.168.86.165"
    private const val ROUTE_AUTH = "auth"
    const val ROUTE_SEND_VERIFICATION_CODE = "$ROUTE_AUTH/send-verification-code"
    const val ROUTE_CHECK_VERIFICATION_CODE = "$ROUTE_AUTH/check-verification-code"
    const val ROUTE_SIGN_UP = "$ROUTE_AUTH/sign-up"
    const val ROUTE_LOG_IN = "$ROUTE_AUTH/log-in"

    private const val ROUTE_IMAGE = "images"
    const val ROUTE_UPLOAD_PROFILE_PIC = "$ROUTE_IMAGE/upload-profile-pic"
    const val ROUTE_GET_PROFILE_PIC = "$ROUTE_IMAGE/get-profile-pic"
}