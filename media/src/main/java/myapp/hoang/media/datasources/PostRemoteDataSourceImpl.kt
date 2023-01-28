package myapp.hoang.media.datasources

import myapp.hoang.media.models.PostForm
import myapp.hoang.media.services.PostService
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService: PostService
): PostRemoteDataSource {
    override suspend fun createPost(postForm: PostForm): String {
        return postService.createPost(postForm)
    }
}