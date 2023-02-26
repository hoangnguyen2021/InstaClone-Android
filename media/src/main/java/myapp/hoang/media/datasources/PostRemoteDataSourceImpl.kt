package myapp.hoang.media.datasources

import kotlinx.coroutines.withContext
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.models.PostForm
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

    override suspend fun getPostsByUser(authorUsername: String): List<InstaClonePost> {
        return withContext(dispatcherProvider.io) {
            postService.getPostsByUser(authorUsername)
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


}