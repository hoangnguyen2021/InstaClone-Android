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
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.LikeIconButtonWithNumber
import myapp.hoang.core_ui.components.ProfilePic
import myapp.hoang.core_ui.components.ReplyClickableText
import myapp.hoang.core_ui.components.UsernameAndTimestamp
import myapp.hoang.media.models.ReplyComment

@Composable
fun ReplyComments(
    commentId: String,
    replyComments: List<ReplyComment>,
    areReplyCommentsLiked: List<Boolean>,
    replyCommentsLikes: List<Int>,
    onLikeReplyComment: (String, String) -> Unit,
    onUnlikeReplyComment: (String, String) -> Unit,
    onReply: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        userScrollEnabled = false,
        modifier = modifier
    ) {
        itemsIndexed(items = replyComments) { i, replyComment ->
            ReplyComment(
                commentId = commentId,
                replyComment = replyComment,
                isLiked = areReplyCommentsLiked[i],
                likes = replyCommentsLikes[i],
                onLikeReplyComment = onLikeReplyComment,
                onUnlikeReplyComment = onUnlikeReplyComment,
                onReply = onReply,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        vertical = LocalDimension.current.small,
                        horizontal = LocalDimension.current.medium
                    )
            )
        }
    }
}

@Composable
fun ReplyComment(
    commentId: String,
    replyComment: ReplyComment,
    isLiked: Boolean,
    likes: Int,
    onLikeReplyComment: (String, String) -> Unit,
    onUnlikeReplyComment: (String, String) -> Unit,
    onReply: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = LocalDimension.current.small,
            alignment = Alignment.Start
        ),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.weight(0.12f)
        ) {}
        ProfilePic(
            path = replyComment.author.profilePicPath,
            onClick = {},
            modifier = Modifier
                .weight(0.08f)
                .size(LocalDimension.current.fourExtraLarge)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(LocalDimension.current.extraSmall),
            modifier = Modifier
                .weight(0.72f)
                .wrapContentHeight()
        ) {
            UsernameAndTimestamp(
                username = replyComment.author.username,
                createdAt = replyComment.createdAt,
                isEdited = replyComment.isEdited,
                onUsernameClick = {},
                modifier = Modifier.wrapContentSize()
            )
            Text(
                text = replyComment.content,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal
            )
            ReplyClickableText(
                onClick = { onReply(replyComment.author.username, commentId) }
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.08f)
                .wrapContentHeight()
        ) {
            LikeIconButtonWithNumber(
                isLiked = isLiked,
                number = likes,
                onClick = {
                    if (isLiked) onUnlikeReplyComment(commentId, replyComment._id)
                    else onLikeReplyComment(commentId, replyComment._id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}