package myapp.hoang.media.datasources

import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.models.PostForm

interface PostRemoteDataSource {
    suspend fun createPost(postForm: PostForm): String
    suspend fun getPostsByUserId(userId: String): List<InstaClonePost>
    suspend fun likePost(postId: String, userId: String): String
    suspend fun unlikePost(postId: String, userId: String): String
    suspend fun getPostById(id: String): InstaClonePost
}