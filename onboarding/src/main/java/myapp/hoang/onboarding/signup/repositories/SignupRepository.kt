package myapp.hoang.onboarding.signup.repositories

import myapp.hoang.onboarding.signup.viewmodels.SignupForm


interface SignupRepository {
    suspend fun sendVerificationCode(recipient: String): String
    suspend fun checkVerificationCode(recipient: String, confirmationCode: String): String
    suspend fun signUp(signupForm: SignupForm): String
}