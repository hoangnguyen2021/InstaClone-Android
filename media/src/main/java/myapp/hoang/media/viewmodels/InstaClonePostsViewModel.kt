package myapp.hoang.media.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import myapp.hoang.media.models.CommentForm
import myapp.hoang.media.models.ReplyCommentForm
import myapp.hoang.media.repositories.PostRepository
import myapp.hoang.settings.repositories.UserPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class InstaClonePostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(InstaClonePostsUiState())
    val uiState = _uiState.asStateFlow()

    private var state: InstaClonePostsUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var getAllPostsByUserJob: Job? = null
    private var likePostJob: Job? = null
    private var unlikePostJob: Job? = null
    private var getPostJob: Job? = null
    private var commentOnPostJob: Job? = null
    private var likeCommentJob: Job? = null
    private var unlikeCommentJob: Job? = null
    private var replyToCommentJob: Job? = null
    private var likeReplyCommentJob: Job? = null
    private var unlikeReplyCommentJob: Job? = null

    fun getAllPostsByUser() {
        getAllPostsByUserJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        getAllPostsByUserJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val posts = postRepository.getPostsByUserId(userId)
                        state = state.copy(
                            posts = posts,
                            arePostsLiked = posts.map { it.likes.contains(userPreferences.id) },
                            postsLikes = posts.map { it.likes.size },
                            isLoading = false
                        )
                        Log.d(TAG, posts.toString())
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun likePost(postId: String) {
        likePostJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        likePostJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val postIndex = state.posts.indexOfFirst { it._id == postId }
                        state = state.copy(
                            arePostsLiked = state.arePostsLiked.toMutableList().apply {
                                this[postIndex] = true
                                toList()
                            },
                            postsLikes = state.postsLikes.toMutableList().apply {
                                this[postIndex] = this[postIndex].plus(1)
                                toList()
                            }
                        )
                        postRepository.likePost(postId, userId)
                        state = state.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun unlikePost(postId: String) {
        unlikePostJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        unlikePostJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val postIndex = state.posts.indexOfFirst { it._id == postId }
                        state = state.copy(
                            arePostsLiked = state.arePostsLiked.toMutableList().apply {
                                this[postIndex] = false
                                toList()
                            },
                            postsLikes = state.postsLikes.toMutableList().apply {
                                this[postIndex] = this[postIndex].minus(1)
                                toList()
                            }
                        )
                        postRepository.unlikePost(postId, userId)
                        state = state.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun getPost(id: String) {
        getPostJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        getPostJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    state = if (userId == null) {
                        state.copy(
                            isLoading = false
                        )
                    } else {
                        val post = postRepository.getPostById(id)
                        Log.d(TAG, post.toString())
                        state.copy(
                            post = post,
                            areCommentsLiked = post.comments.map { it.likes.contains(userPreferences.id) },
                            commentsLikes = post.comments.map { it.likes.size },
                            areReplyCommentsLiked = post.comments.map { comment ->
                                comment.replies.map { replyComment ->
                                    replyComment.likes.contains(userPreferences.id)
                                }
                            },
                            replyCommentsLikes = post.comments.map { comment ->
                                comment.replies.map { replyComment ->
                                    replyComment.likes.size
                                }
                            },
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun reloadPost(id: String) {
        getPost(id)
    }

    fun commentOnPost(postId: String, content: String) {
        commentOnPostJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        commentOnPostJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val commentForm = CommentForm(
                            authorId = userId,
                            postId = postId,
                            content = content,
                            createdAt = Clock.System.now(),
                            lastEditedAt = Clock.System.now()
                        )
                        postRepository.commentOnPost(commentForm)
                        state = state.copy(
                            isLoading = false
                        )
                        reloadPost(postId)
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun likeComment(commentId: String) {
        likeCommentJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        likeCommentJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null || state.post == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val commentIndex =
                            state.post!!.comments.indexOfFirst { it._id == commentId }
                        state = state.copy(
                            areCommentsLiked = state.areCommentsLiked.toMutableList().apply {
                                if (commentIndex != -1)
                                    this[commentIndex] = true
                                toList()
                            },
                            commentsLikes = state.commentsLikes.toMutableList().apply {
                                if (commentIndex != -1)
                                    this[commentIndex] = this[commentIndex].plus(1)
                                toList()
                            }
                        )
                        postRepository.likeComment(commentId, userId)
                        state = state.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun unlikeComment(commentId: String) {
        unlikeCommentJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        unlikeCommentJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null || state.post == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val commentIndex =
                            state.post!!.comments.indexOfFirst { it._id == commentId }
                        state = state.copy(
                            areCommentsLiked = state.areCommentsLiked.toMutableList().apply {
                                this[commentIndex] = false
                                toList()
                            },
                            commentsLikes = state.commentsLikes.toMutableList().apply {
                                this[commentIndex] = this[commentIndex].minus(1)
                                toList()
                            }
                        )
                        postRepository.unlikeComment(commentId, userId)
                        state = state.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun replyToComment(postId: String, commentId: String, content: String) {
        replyToCommentJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        replyToCommentJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val replyCommentForm = ReplyCommentForm(
                            authorId = userId,
                            commentId = commentId,
                            content = content,
                            createdAt = Clock.System.now(),
                            lastEditedAt = Clock.System.now()
                        )
                        postRepository.replyToComment(replyCommentForm)
                        state = state.copy(
                            isLoading = false
                        )
                        reloadPost(postId)
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun likeReplyComment(commentId: String, replyCommentId: String) {
        likeReplyCommentJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        likeReplyCommentJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null || state.post == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val commentIndex =
                            state.post!!.comments.indexOfFirst { it._id == commentId }
                        val replyCommentIndex =
                            state.post!!.comments[commentIndex].replies.indexOfFirst { it._id == replyCommentId }
                        state = state.copy(
                            areReplyCommentsLiked = state.areReplyCommentsLiked.toMutableList()
                                .apply {
                                    this[commentIndex] = this[commentIndex].toMutableList().apply {
                                        this[replyCommentIndex] = true
                                        toList()
                                    }
                                    toList()
                                },
                            replyCommentsLikes = state.replyCommentsLikes.toMutableList().apply {
                                this[commentIndex] = this[commentIndex].toMutableList().apply {
                                    this[replyCommentIndex] = this[replyCommentIndex].plus(1)
                                    toList()
                                }
                                toList()
                            }
                        )
                        postRepository.likeReplyComment(replyCommentId, userId)
                        state = state.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun unlikeReplyComment(commentId: String, replyCommentId: String) {
        unlikeReplyCommentJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        unlikeReplyCommentJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null || state.post == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val commentIndex =
                            state.post!!.comments.indexOfFirst { it._id == commentId }
                        val replyCommentIndex =
                            state.post!!.comments[commentIndex].replies.indexOfFirst { it._id == replyCommentId }
                        state = state.copy(
                            areReplyCommentsLiked = state.areReplyCommentsLiked.toMutableList()
                                .apply {
                                    this[commentIndex] = this[commentIndex].toMutableList().apply {
                                        this[replyCommentIndex] = false
                                        toList()
                                    }
                                    toList()
                                },
                            replyCommentsLikes = state.replyCommentsLikes.toMutableList().apply {
                                this[commentIndex] = this[commentIndex].toMutableList().apply {
                                    this[replyCommentIndex] = this[replyCommentIndex].minus(1)
                                    toList()
                                }
                                toList()
                            }
                        )
                        postRepository.unlikeReplyComment(replyCommentId, userId)
                        state = state.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    companion object {
        private val TAG = InstaClonePostsViewModel::class.java.simpleName
    }
}