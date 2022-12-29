package myapp.hoang.core.config

object NetworkConfig {
    const val HOST = "192.168.1.65"
    private const val ROUTE_AUTH = "auth"
    const val ROUTE_SEND_VERIFICATION_CODE = "$ROUTE_AUTH/send-verification-code"
    const val ROUTE_CHECK_VERIFICATION_CODE = "$ROUTE_AUTH/check-verification-code"
}