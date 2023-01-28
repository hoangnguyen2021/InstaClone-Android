package myapp.hoang.media.repositories

import myapp.hoang.media.datasources.PostRemoteDataSource
import myapp.hoang.media.models.PostForm

class PostRepositoryImpl(
    private val postRemoteDataSource: PostRemoteDataSource
): PostRepository {
    override suspend fun createPost(postForm: PostForm): String {
        return postRemoteDataSource.createPost(postForm)
    }
}