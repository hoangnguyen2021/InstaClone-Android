package myapp.hoang.onboarding.signup.services

import myapp.hoang.onboarding.signup.services.models.VerificationCheckResponse
import myapp.hoang.onboarding.signup.services.models.VerificationResponse

interface SignupService {
    suspend fun sendVerificationCode(recipient: String): VerificationResponse

    suspend fun checkVerificationCode(recipient: String, confirmationCode: String): VerificationCheckResponse
}