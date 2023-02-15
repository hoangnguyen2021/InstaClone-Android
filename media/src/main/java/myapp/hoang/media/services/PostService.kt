package myapp.hoang.media.services

import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.models.PostForm

interface PostService {
    suspend fun createPost(postForm: PostForm): String
    suspend fun getPostsByUser(authorUsername: String): List<InstaClonePost>
}