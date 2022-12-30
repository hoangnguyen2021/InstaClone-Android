package myapp.hoang.onboarding.signup.datasources

import myapp.hoang.onboarding.signup.viewmodels.SignupForm

interface SignupRemoteDataSource {
    suspend fun sendVerificationCode(recipient: String): String
    suspend fun checkVerificationCode(recipient: String, confirmationCode: String): String
    suspend fun signUp(signupForm: SignupForm): String
}