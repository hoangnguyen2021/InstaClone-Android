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
import myapp.hoang.core_ui.components.ProfilePic
import myapp.hoang.core_ui.components.ReplyClickableText
import myapp.hoang.core_ui.components.UsernameAndTimestamp
import myapp.hoang.media.models.Comment

@Composable
fun Comments(
    comments: List<Comment>,
    authors: List<InstaCloneUser>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        itemsIndexed(items = comments) { i, comment ->
            Comment(
                author = authors[i],
                comment = comment
            )
        }
    }
}

@Composable
fun Comment(
    author: InstaCloneUser,
    comment: Comment,
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
        ProfilePic(
            path = author.profilePicPath,
            onClick = {},
            modifier = Modifier
                .weight(0.12f)
                .size(LocalDimension.current.fourExtraLarge)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(LocalDimension.current.extraSmall),
            modifier = Modifier
                .weight(0.88f)
                .wrapContentHeight()
        ) {
            UsernameAndTimestamp(
                username = author.username,
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
    }
}