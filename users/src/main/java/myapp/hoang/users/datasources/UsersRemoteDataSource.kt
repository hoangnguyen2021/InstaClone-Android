package myapp.hoang.users.datasources

import myapp.hoang.core.models.InstaCloneUser

interface UsersRemoteDataSource {
    suspend fun getUserById(id: String): InstaCloneUser
}