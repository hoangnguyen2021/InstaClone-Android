package myapp.hoang.media.repositories

import myapp.hoang.media.models.PostForm

interface PostRepository {
    suspend fun createPost(postForm: PostForm): String
}