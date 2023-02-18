package myapp.hoang.instaclone.features.users.datasources

import myapp.hoang.instaclone.models.InstaCloneUser

interface UsersRemoteDataSource {
    suspend fun getUserById(id: String): InstaCloneUser
}