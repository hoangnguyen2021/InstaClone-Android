package myapp.hoang.core.config

object NetworkConfig {
    const val HOST = "localhost"
    const val ROUTE_SIGNUP = "/sign-up"
    const val ROUTE_SIGNUP_CHECK_VERIFICATION_CODE = "$ROUTE_SIGNUP/check-verification-code"
    const val ROUTE_SIGNUP_VERIFY_VERIFICATION_CODE = "$ROUTE_SIGNUP/verify-verification-code"
}