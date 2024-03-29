package myapp.hoang.media.datasources

import kotlinx.coroutines.withContext
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.media.models.CommentForm
import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.models.PostForm
import myapp.hoang.media.models.ReplyCommentForm
import myapp.hoang.media.services.PostService
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService: PostService,
    private val dispatcherProvider: DispatcherProvider
): PostRemoteDataSource {
    override suspend fun createPost(postForm: PostForm): String {
        return withContext(dispatcherProvider.io) {
            postService.createPost(postForm)
        }
    }

    override suspend fun getPostsByUserId(userId: String): List<InstaClonePost> {
        return withContext(dispatcherProvider.io) {
            postService.getPostsByUserId(userId)
        }
    }

    override suspend fun likePost(postId: String, userId: String): String {
        return withContext(dispatcherProvider.io) {
            postService.likePost(postId, userId)
        }
    }

    override suspend fun unlikePost(postId: String, userId: String): String {
        return withContext(dispatcherProvider.io) {
            postService.unlikePost(postId, userId)
        }
    }

    override suspend fun getPostById(id: String): InstaClonePost {
        return withContext(dispatcherProvider.io) {
            postService.getPostById(id)
        }
    }

    override suspend fun commentOnPost(commentForm: CommentForm): String {
        return withContext(dispatcherProvider.io) {
            postService.commentOnPost(commentForm)
        }
    }

    override suspend fun likeComment(commentId: String, userId: String): String {
        return withContext(dispatcherProvider.io) {
            postService.likeComment(commentId, userId)
        }
    }

    override suspend fun unlikeComment(commentId: String, userId: String): String {
        return withContext(dispatcherProvider.io) {
            postService.unlikeComment(commentId, userId)
        }
    }

    override suspend fun replyToComment(replyCommentForm: ReplyCommentForm): String {
        return withContext(dispatcherProvider.io) {
            postService.replyToComment(replyCommentForm)
        }
    }

    override suspend fun likeReplyComment(replyCommentId: String, userId: String): String {
        return withContext(dispatcherProvider.io) {
            postService.likeReplyComment(replyCommentId, userId)
        }
    }

    override suspend fun unlikeReplyComment(replyCommentId: String, userId: String): String {
        return withContext(dispatcherProvider.io) {
            postService.unlikeReplyComment(replyCommentId, userId)
        }
    }
}