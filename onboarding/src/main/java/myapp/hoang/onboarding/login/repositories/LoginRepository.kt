package myapp.hoang.onboarding.login.repositories

import myapp.hoang.onboarding.login.models.AuthResponse
import myapp.hoang.onboarding.login.models.LoginForm


interface LoginRepository {
    suspend fun logIn(loginForm: LoginForm): AuthResponse
}