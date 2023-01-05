package myapp.hoang.onboarding.signup.services

import myapp.hoang.onboarding.signup.models.SignupForm

interface SignupService {
    suspend fun sendVerificationCode(recipient: String): String
    suspend fun checkVerificationCode(recipient: String, confirmationCode: String): String
    suspend fun signUp(signupForm: SignupForm): String
}