package myapp.hoang.media.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.LikeIconButtonWithNumber
import myapp.hoang.core_ui.components.ProfilePic
import myapp.hoang.core_ui.components.ReplyClickableText
import myapp.hoang.core_ui.components.UsernameAndTimestamp
import myapp.hoang.media.models.Comment

@Composable
fun Comments(
    comments: List<Comment>,
    areCommentsLiked: List<Boolean>,
    commentsLikes: List<Int>,
    areReplyCommentLiked: List<List<Boolean>>,
    replyCommentsLikes: List<List<Int>>,
    onLikeComment: (String) -> Unit,
    onUnlikeComment: (String) -> Unit,
    onLikeReplyComment: (String, String) -> Unit,
    onUnlikeReplyComment: (String, String) -> Unit,
    onReply: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(items = comments) { i, comment ->
            Comment(
                comment = comment,
                isCommentLiked = areCommentsLiked[i],
                commentLikes = commentsLikes[i],
                areReplyCommentLiked = areReplyCommentLiked[i],
                replyCommentsLikes = replyCommentsLikes[i],
                onLikeComment = onLikeComment,
                onUnlikeComment = onUnlikeComment,
                onLikeReplyComment = onLikeReplyComment,
                onUnlikeReplyComment = onUnlikeReplyComment,
                onReply = onReply,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
fun Comment(
    comment: Comment,
    isCommentLiked: Boolean,
    commentLikes: Int,
    areReplyCommentLiked: List<Boolean>,
    replyCommentsLikes: List<Int>,
    onLikeComment: (String) -> Unit,
    onUnlikeComment: (String) -> Unit,
    onLikeReplyComment: (String, String) -> Unit,
    onUnlikeReplyComment: (String, String) -> Unit,
    onReply: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = LocalDimension.current.small,
                alignment = Alignment.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    vertical = LocalDimension.current.small,
                    horizontal = LocalDimension.current.medium
                )
        ) {
            ProfilePic(
                path = comment.author.profilePicPath,
                onClick = {},
                modifier = Modifier
                    .weight(0.12f)
                    .size(LocalDimension.current.fourExtraLarge)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(LocalDimension.current.extraSmall),
                modifier = Modifier
                    .weight(0.8f)
                    .wrapContentHeight()
            ) {
                UsernameAndTimestamp(
                    username = comment.author.username,
                    createdAt = comment.createdAt,
                    isEdited = comment.isEdited,
                    onUsernameClick = {},
                    modifier = Modifier.wrapContentSize()
                )
                Text(
                    text = comment.content,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                ReplyClickableText(
                    onClick = { onReply(comment.author.username, comment._id) }
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(0.08f)
                    .wrapContentHeight()
            ) {
                LikeIconButtonWithNumber(
                    isLiked = isCommentLiked,
                    number = commentLikes,
                    onClick = {
                        if (isCommentLiked) onUnlikeComment(comment._id)
                        else onLikeComment(comment._id)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
        ReplyComments(
            commentId = comment._id,
            replyComments = comment.replies,
            areReplyCommentsLiked = areReplyCommentLiked,
            replyCommentsLikes = replyCommentsLikes,
            onLikeReplyComment = onLikeReplyComment,
            onUnlikeReplyComment = onUnlikeReplyComment,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 9999.dp) // hack for nested LazyColumn
        )
    }
}