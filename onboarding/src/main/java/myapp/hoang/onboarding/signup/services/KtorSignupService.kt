package myapp.hoang.onboarding.signup.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import myapp.hoang.core.config.NetworkConfig.ROUTE_CHECK_VERIFICATION_CODE
import myapp.hoang.core.config.NetworkConfig.ROUTE_SEND_VERIFICATION_CODE
import myapp.hoang.onboarding.signup.services.models.VerificationCheckResponse
import myapp.hoang.onboarding.signup.services.models.VerificationResponse
import javax.inject.Inject

class KtorSignupService @Inject constructor(
    private val client: HttpClient
): SignupService {
    override suspend fun sendVerificationCode(mobileNumber: String): VerificationResponse {
        return client.get {
            url(ROUTE_SEND_VERIFICATION_CODE)
            parameter("mobile-number", mobileNumber)
        }.body()
    }

    override suspend fun checkVerificationCode(mobileNumber: String, code: String): VerificationCheckResponse {
        return client.get {
            url(ROUTE_CHECK_VERIFICATION_CODE)
            parameter("mobile-number", mobileNumber)
            parameter("code", code)
        }.body()
    }
}