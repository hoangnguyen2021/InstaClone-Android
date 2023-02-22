package myapp.hoang.instaclone.features.users.datasources

import myapp.hoang.core.models.InstaCloneUser

interface UsersRemoteDataSource {
    suspend fun getUserById(id: String): InstaCloneUser
}