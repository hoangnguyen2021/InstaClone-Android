package myapp.hoang.onboarding.signup.repositories

import myapp.hoang.onboarding.signup.datasources.SignupRemoteDataSource
import myapp.hoang.onboarding.signup.services.models.VerificationCheckResponse
import myapp.hoang.onboarding.signup.services.models.VerificationResponse
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val signupRemoteDataSource: SignupRemoteDataSource
) : SignupRepository {
    override suspend fun sendVerificationCode(mobileNumber: String): VerificationResponse {
        return signupRemoteDataSource.sendVerificationCode(mobileNumber)
    }

    override suspend fun checkVerificationCode(
        mobileNumber: String,
        code: String
    ): VerificationCheckResponse {
        return signupRemoteDataSource.checkVerificationCode(mobileNumber, code)
    }
}