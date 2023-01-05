package myapp.hoang.onboarding.signup.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import myapp.hoang.core.config.NetworkConfig.ROUTE_CHECK_VERIFICATION_CODE
import myapp.hoang.core.config.NetworkConfig.ROUTE_SEND_VERIFICATION_CODE
import myapp.hoang.core.config.NetworkConfig.ROUTE_SIGN_UP
import myapp.hoang.onboarding.signup.models.SignupForm
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

    override suspend fun signUp(signupForm: SignupForm): String {
        return client.post {
            url(ROUTE_SIGN_UP)
            contentType(ContentType.Application.Json)
            setBody(signupForm)
        }.body()
    }
}