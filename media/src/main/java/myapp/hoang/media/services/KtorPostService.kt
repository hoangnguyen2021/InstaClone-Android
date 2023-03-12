package myapp.hoang.media.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import myapp.hoang.core.config.NetworkConfig.ROUTE_COMMENT_ON_POST
import myapp.hoang.core.config.NetworkConfig.ROUTE_CREATE_POST
import myapp.hoang.core.config.NetworkConfig.ROUTE_GET_POSTS_BY_USER_ID
import myapp.hoang.core.config.NetworkConfig.ROUTE_GET_POST_BY_ID
import myapp.hoang.core.config.NetworkConfig.ROUTE_LIKE_COMMENT
import myapp.hoang.core.config.NetworkConfig.ROUTE_LIKE_POST
import myapp.hoang.core.config.NetworkConfig.ROUTE_REPLY_TO_COMMENT
import myapp.hoang.core.config.NetworkConfig.ROUTE_UNLIKE_COMMENT
import myapp.hoang.core.config.NetworkConfig.ROUTE_UNLIKE_POST
import myapp.hoang.media.models.CommentForm
import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.models.PostForm
import myapp.hoang.media.models.ReplyCommentForm
import javax.inject.Inject

class KtorPostService @Inject constructor(
    private val client: HttpClient
) : PostService {
    override suspend fun createPost(postForm: PostForm): String {
        return client.post {
            url(ROUTE_CREATE_POST)
            setBody(postForm)
        }.body()
    }

    override suspend fun getPostsByUserId(userId: String): List<InstaClonePost> {
        return client.get {
            url(ROUTE_GET_POSTS_BY_USER_ID)
            parameter("userId", userId)
        }.body()
    }

    override suspend fun likePost(postId: String, userId: String): String {
        return client.put {
            url(ROUTE_LIKE_POST)
            parameter("postId", postId)
            parameter("userId", userId)
        }.body()
    }

    override suspend fun unlikePost(postId: String, userId: String): String {
        return client.put {
            url(ROUTE_UNLIKE_POST)
            parameter("postId", postId)
            parameter("userId", userId)
        }.body()
    }

    override suspend fun getPostById(id: String): InstaClonePost {
        return client.get {
            url(ROUTE_GET_POST_BY_ID)
            parameter("id", id)
        }.body()
    }

    override suspend fun commentOnPost(commentForm: CommentForm): String {
        return client.post {
            url(ROUTE_COMMENT_ON_POST)
            setBody(commentForm)
        }.body()
    }

    override suspend fun likeComment(commentId: String, userId: String): String {
        return client.put {
            url(ROUTE_LIKE_COMMENT)
            parameter("commentId", commentId)
            parameter("userId", userId)
        }.body()
    }

    override suspend fun unlikeComment(commentId: String, userId: String): String {
        return client.put {
            url(ROUTE_UNLIKE_COMMENT)
            parameter("commentId", commentId)
            parameter("userId", userId)
        }.body()
    }

    override suspend fun replyToComment(replyCommentForm: ReplyCommentForm): String {
        return client.post {
            url(ROUTE_REPLY_TO_COMMENT)
            setBody(replyCommentForm)
        }.body()
    }
}