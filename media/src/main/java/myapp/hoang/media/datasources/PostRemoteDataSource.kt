package myapp.hoang.media.datasources

import myapp.hoang.media.models.PostForm

interface PostRemoteDataSource {
    suspend fun createPost(postForm: PostForm): String
}