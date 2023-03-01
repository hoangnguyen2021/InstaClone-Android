package myapp.hoang.media.repositories

import myapp.hoang.media.models.CommentForm
import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.models.PostForm

interface PostRepository {
    suspend fun createPost(postForm: PostForm): String
    suspend fun getPostsByUserId(userId: String): List<InstaClonePost>
    suspend fun likePost(postId: String, userId: String): String
    suspend fun unlikePost(postId: String, userId: String): String
    suspend fun getPostById(id: String): InstaClonePost
    suspend fun commentOnPost(commentForm: CommentForm): String
}