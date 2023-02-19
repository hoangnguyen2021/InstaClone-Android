package myapp.hoang.onboarding.login.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import myapp.hoang.core.config.NetworkConfig.ROUTE_AUTHENTICATE
import myapp.hoang.core.config.NetworkConfig.ROUTE_LOG_IN
import myapp.hoang.onboarding.login.models.AuthResponse
import myapp.hoang.onboarding.login.models.LoginForm
import javax.inject.Inject

class KtorLoginService @Inject constructor(
    private val client: HttpClient
) : LoginService {
    override suspend fun logIn(loginForm: LoginForm): AuthResponse {
        return client.post {
            url(ROUTE_LOG_IN)
            setBody(loginForm)
        }.body()
    }

    override suspend fun authenticate(token: String) {
        return client.get {
            url(ROUTE_AUTHENTICATE)
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }.body()
    }
}