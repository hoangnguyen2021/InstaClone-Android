package myapp.hoang.media.repositories

import myapp.hoang.media.datasources.PostRemoteDataSource
import myapp.hoang.media.models.CommentForm
import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.models.PostForm

class PostRepositoryImpl(
    private val postRemoteDataSource: PostRemoteDataSource
): PostRepository {
    override suspend fun createPost(postForm: PostForm): String {
        return postRemoteDataSource.createPost(postForm)
    }

    override suspend fun getPostsByUserId(userId: String): List<InstaClonePost> {
        return postRemoteDataSource.getPostsByUserId(userId)
    }

    override suspend fun likePost(postId: String, userId: String): String {
        return postRemoteDataSource.likePost(postId, userId)
    }

    override suspend fun unlikePost(postId: String, userId: String): String {
        return postRemoteDataSource.unlikePost(postId, userId)
    }

    override suspend fun getPostById(id: String): InstaClonePost {
        return postRemoteDataSource.getPostById(id)
    }

    override suspend fun commentOnPost(commentForm: CommentForm): String {
        return postRemoteDataSource.commentOnPost(commentForm)
    }
}