package myapp.hoang.onboarding.signup.datasources

import kotlinx.coroutines.withContext
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.onboarding.signup.services.SignupService
import myapp.hoang.onboarding.signup.models.SignupForm
import javax.inject.Inject

class SignupRemoteDataSourceImpl @Inject constructor(
    private val signupService: SignupService,
    private val dispatcherProvider: DispatcherProvider
): SignupRemoteDataSource {
    override suspend fun sendVerificationCode(recipient: String): String {
        return withContext(dispatcherProvider.io) {
            signupService.sendVerificationCode(recipient)
        }
    }

    override suspend fun checkVerificationCode(
        recipient: String,
        confirmationCode: String
    ): String {
        return withContext(dispatcherProvider.io) {
            signupService.checkVerificationCode(recipient, confirmationCode)
        }
    }

    override suspend fun signUp(signupForm: SignupForm): String {
        return withContext(dispatcherProvider.io) {
            signupService.signUp(signupForm)
        }
    }
}