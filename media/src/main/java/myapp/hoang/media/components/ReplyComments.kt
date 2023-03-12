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
import myapp.hoang.core.models.InstaCloneUser
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.LikeIconButtonWithNumber
import myapp.hoang.core_ui.components.ProfilePic
import myapp.hoang.core_ui.components.ReplyClickableText
import myapp.hoang.core_ui.components.UsernameAndTimestamp
import myapp.hoang.media.models.ReplyComment

@Composable
fun ReplyComments(
    comments: List<ReplyComment>,
    areReplyCommentsLiked: List<Boolean>,
    replyCommentsLikes: List<Int>,
    onLike: (String) -> Unit,
    onUnlike: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        userScrollEnabled = false,
        modifier = modifier
    ) {
        itemsIndexed(items = comments) { i, comment ->
            ReplyComment(
                commenter = comment.author,
                comment = comment,
                isLiked = areReplyCommentsLiked[i],
                likes = replyCommentsLikes[i],
                onLike = onLike,
                onUnlike = onUnlike,
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
    comment: ReplyComment,
    commenter: InstaCloneUser,
    isLiked: Boolean,
    likes: Int,
    onLike: (String) -> Unit,
    onUnlike: (String) -> Unit,
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
            path = commenter.profilePicPath,
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
                username = commenter.username,
                createdAt = comment.createdAt,
                isEdited = comment.isEdited,
                onUsernameClick = {},
                modifier = Modifier.wrapContentSize()
            )
            Text(
                text = comment.content,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal
            )
            ReplyClickableText(
                onClick = {}
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
                    if (isLiked) onUnlike(comment._id)
                    else onLike(comment._id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}