package myapp.hoang.onboarding.login.services

import myapp.hoang.onboarding.login.models.AuthResponse
import myapp.hoang.onboarding.login.models.LoginForm

interface LoginService {
    suspend fun logIn(loginForm: LoginForm): AuthResponse
    suspend fun authenticate(token: String)
}