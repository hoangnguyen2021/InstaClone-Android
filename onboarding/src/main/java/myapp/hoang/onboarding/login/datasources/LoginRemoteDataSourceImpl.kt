package myapp.hoang.onboarding.login.datasources

import kotlinx.coroutines.withContext
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.onboarding.login.models.AuthResponse
import myapp.hoang.onboarding.login.models.LoginForm
import myapp.hoang.onboarding.login.services.LoginService
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val loginService: LoginService,
    private val dispatcherProvider: DispatcherProvider
) : LoginRemoteDataSource {
    override suspend fun logIn(loginForm: LoginForm): AuthResponse {
        return withContext(dispatcherProvider.io) {
            loginService.logIn(loginForm)
        }
    }

    override suspend fun authenticate(token: String) {
        withContext(dispatcherProvider.io) {
            loginService.authenticate(token)
        }
    }
}