package myapp.hoang.onboarding.signup.datasources

import myapp.hoang.onboarding.signup.services.models.VerificationCheckResponse
import myapp.hoang.onboarding.signup.services.models.VerificationResponse

interface SignupRemoteDataSource {
    suspend fun sendVerificationCode(recipient: String): VerificationResponse
    suspend fun checkVerificationCode(recipient: String, confirmationCode: String)
            : VerificationCheckResponse
}