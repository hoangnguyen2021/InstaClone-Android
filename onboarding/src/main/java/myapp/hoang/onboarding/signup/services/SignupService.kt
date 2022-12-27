package myapp.hoang.onboarding.signup.services

import myapp.hoang.onboarding.signup.services.models.VerificationCheckResponse
import myapp.hoang.onboarding.signup.services.models.VerificationResponse

interface SignupService {
    suspend fun sendVerificationCode(mobileNumber: String): VerificationResponse

    suspend fun checkVerificationCode(mobileNumber: String, code: String): VerificationCheckResponse
}