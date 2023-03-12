package myapp.hoang.instaclone.models

sealed class CommentMode {
    object Comment : CommentMode()
    data class Reply(
        val username: String,
        val commentId: String
    ) : CommentMode()
}