package myapp.hoang.onboarding.signup.repositories

import myapp.hoang.onboarding.signup.datasources.SignupRemoteDataSource
import myapp.hoang.onboarding.signup.models.SignupForm
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val signupRemoteDataSource: SignupRemoteDataSource
) : SignupRepository {
    override suspend fun sendVerificationCode(recipient: String): String {
        return signupRemoteDataSource.sendVerificationCode(recipient)
    }

    override suspend fun checkVerificationCode(
        recipient: String,
        confirmationCode: String
    ): String {
        return signupRemoteDataSource.checkVerificationCode(recipient, confirmationCode)
    }

    override suspend fun signUp(signupForm: SignupForm): String {
        return signupRemoteDataSource.signUp(signupForm)
    }
}