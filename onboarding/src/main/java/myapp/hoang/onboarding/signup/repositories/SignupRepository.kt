package myapp.hoang.onboarding.signup.repositories

import myapp.hoang.onboarding.signup.services.models.VerificationCheckResponse
import myapp.hoang.onboarding.signup.services.models.VerificationResponse

interface SignupRepository {
    suspend fun sendVerificationCode(recipient: String): VerificationResponse
    suspend fun checkVerificationCode(recipient: String, confirmationCode: String): VerificationCheckResponse
}