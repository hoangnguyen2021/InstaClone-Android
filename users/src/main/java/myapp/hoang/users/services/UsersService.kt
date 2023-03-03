package myapp.hoang.users.services

import myapp.hoang.core.models.InstaCloneUser

interface UsersService {
    suspend fun getUserById(id: String): InstaCloneUser
    suspend fun getCommentorsByPostId(postId: String): List<InstaCloneUser>
}