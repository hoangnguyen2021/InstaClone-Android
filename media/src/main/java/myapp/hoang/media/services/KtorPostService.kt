package myapp.hoang.media.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import myapp.hoang.core.config.NetworkConfig.ROUTE_CREATE_POST
import myapp.hoang.core.config.NetworkConfig.ROUTE_GET_POSTS_BY_USER
import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.models.PostForm
import javax.inject.Inject

class KtorPostService @Inject constructor(
    private val client: HttpClient
): PostService {
    override suspend fun createPost(postForm: PostForm): String {
        return client.post {
            url(ROUTE_CREATE_POST)
            setBody(postForm)
        }.body()
    }

    override suspend fun getPostsByUser(authorUsername: String): List<InstaClonePost> {
        return client.get {
            url(ROUTE_GET_POSTS_BY_USER)
            parameter("authorUsername", authorUsername)
        }.body()
    }
}