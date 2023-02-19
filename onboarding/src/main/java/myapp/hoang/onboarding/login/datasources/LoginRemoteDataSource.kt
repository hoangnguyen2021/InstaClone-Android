package myapp.hoang.onboarding.login.datasources

import myapp.hoang.onboarding.login.models.AuthResponse
import myapp.hoang.onboarding.login.models.LoginForm

interface LoginRemoteDataSource {
    suspend fun logIn(loginForm: LoginForm): AuthResponse
    suspend fun authenticate(token: String)
}