package myapp.hoang.onboarding.signup.repositories

import myapp.hoang.onboarding.signup.services.models.VerificationCheckResponse
import myapp.hoang.onboarding.signup.services.models.VerificationResponse

interface SignupRepository {
    suspend fun sendVerificationCode(mobileNumber: String): VerificationResponse
    suspend fun checkVerificationCode(mobileNumber: String, code: String): VerificationCheckResponse
}