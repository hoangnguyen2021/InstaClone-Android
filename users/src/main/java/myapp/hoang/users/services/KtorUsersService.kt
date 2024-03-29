package myapp.hoang.users.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import myapp.hoang.core.config.NetworkConfig.ROUTE_USERS
import myapp.hoang.core.models.InstaCloneUser
import javax.inject.Inject

class KtorUsersService @Inject constructor(
    private val httpClient: HttpClient
): UsersService {
    override suspend fun getUserById(id: String): InstaCloneUser {
        return httpClient.get {
            url(ROUTE_USERS)
            parameter("id", id)
        }.body()
    }
}