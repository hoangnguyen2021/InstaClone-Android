package myapp.hoang.onboarding.signup.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import myapp.hoang.core.config.NetworkConfig.ROUTE_CHECK_VERIFICATION_CODE
import myapp.hoang.core.config.NetworkConfig.ROUTE_SEND_VERIFICATION_CODE
import javax.inject.Inject

class KtorSignupService @Inject constructor(
    private val client: HttpClient
) : SignupService {
    override suspend fun sendVerificationCode(recipient: String): String {
        return client.get {
            url(ROUTE_SEND_VERIFICATION_CODE)
            parameter("recipient", recipient)
        }.body()
    }

    override suspend fun checkVerificationCode(
        recipient: String,
        confirmationCode: String
    ): String {
        return client.get {
            url(ROUTE_CHECK_VERIFICATION_CODE)
            parameter("recipient", recipient)
            parameter("verification-code", confirmationCode)
        }.body()
    }
}