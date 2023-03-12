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
import myapp.hoang.core.models.InstaCloneUser
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
    onLike: (String) -> Unit,
    onUnlike: (String) -> Unit,
    onReply: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(items = comments) { i, comment ->
            Comment(
                commenter = comment.author,
                comment = comment,
                isCommentLiked = areCommentsLiked[i],
                commentLikes = commentsLikes[i],
                areReplyCommentLiked = areReplyCommentLiked[i],
                replyCommentsLikes = replyCommentsLikes[i],
                onLike = onLike,
                onUnlike = onUnlike,
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
    commenter: InstaCloneUser,
    isCommentLiked: Boolean,
    commentLikes: Int,
    areReplyCommentLiked: List<Boolean>,
    replyCommentsLikes: List<Int>,
    onLike: (String) -> Unit,
    onUnlike: (String) -> Unit,
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
                path = commenter.profilePicPath,
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
                    username = commenter.username,
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
                    onClick = { onReply(commenter.username, comment._id) }
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
                        if (isCommentLiked) onUnlike(comment._id)
                        else onLike(comment._id)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
        ReplyComments(
            comments = comment.replies,
            areReplyCommentsLiked = areReplyCommentLiked,
            replyCommentsLikes = replyCommentsLikes,
            onLike = {},
            onUnlike = {},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 9999.dp) // hack for nested LazyColumn
        )
    }
}