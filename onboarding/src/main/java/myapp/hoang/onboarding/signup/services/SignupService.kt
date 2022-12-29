package myapp.hoang.onboarding.signup.services

interface SignupService {
    suspend fun sendVerificationCode(recipient: String): String

    suspend fun checkVerificationCode(recipient: String, confirmationCode: String): String
}