package myapp.hoang.onboarding.login.repositories

import myapp.hoang.onboarding.login.datasources.LoginRemoteDataSource
import myapp.hoang.onboarding.login.models.AuthResponse
import myapp.hoang.onboarding.login.models.LoginForm

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource
): LoginRepository {
    override suspend fun logIn(loginForm: LoginForm): AuthResponse {
        return loginRemoteDataSource.logIn(loginForm)
    }

    override suspend fun authenticate(token: String) {
        loginRemoteDataSource.authenticate(token)
    }
}