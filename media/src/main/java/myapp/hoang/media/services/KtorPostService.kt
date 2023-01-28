package myapp.hoang.media.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import myapp.hoang.core.config.NetworkConfig.ROUTE_CREATE_POST
import myapp.hoang.media.models.PostForm
import javax.inject.Inject

class KtorPostService @Inject constructor(
    private val client: HttpClient
): PostService {
    override suspend fun createPost(postForm: PostForm): String {
        return client.post {
            url(ROUTE_CREATE_POST)
            contentType(ContentType.Application.Json)
            setBody(postForm)
        }.body()
    }
}