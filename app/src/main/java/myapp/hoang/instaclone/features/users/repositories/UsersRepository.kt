package myapp.hoang.instaclone.features.users.repositories

import myapp.hoang.core.models.InstaCloneUser

interface UsersRepository {
    suspend fun getUserById(id: String): InstaCloneUser
}