package myapp.hoang.instaclone.features.users.services

import myapp.hoang.core.models.InstaCloneUser

interface UsersService {
    suspend fun getUserById(id: String): InstaCloneUser
}