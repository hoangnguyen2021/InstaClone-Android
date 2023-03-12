package myapp.hoang.instaclone.screens.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core.models.InstaCloneUser
import myapp.hoang.settings.models.UserPreferences
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.*
import myapp.hoang.instaclone.R
import myapp.hoang.instaclone.models.CommentMode
import myapp.hoang.media.components.Comments
import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.viewmodels.InstaClonePostsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CommentsScreen(
    userPreferences: UserPreferences,
    postsViewModel: InstaClonePostsViewModel = hiltViewModel(),
    postId: String,
    onBack: () -> Unit
) {
    val postsUiState by postsViewModel.uiState.collectAsStateWithLifecycle()

    var commentContent by remember { mutableStateOf("") }
    var commentMode by remember { mutableStateOf<CommentMode>(CommentMode.Comment) }

    LaunchedEffect(key1 = true) {
        postsViewModel.getPost(postId)
    }

    LaunchedEffect(key1 = postsUiState.post) {
        postsUiState.post?.let {
            postsViewModel.getAuthorById(it.authorId)
        }
    }

    if (postsUiState.author != null && postsUiState.post != null) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CommentsTopBar(
                    onBack = onBack
                )
                Caption(
                    author = postsUiState.author!!,
                    post = postsUiState.post!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            vertical = LocalDimension.current.small,
                            horizontal = LocalDimension.current.medium
                        )
                )
                Comments(
                    comments = postsUiState.post!!.comments,
                    areCommentsLiked = postsUiState.areCommentsLiked,
                    commentsLikes = postsUiState.commentsLikes,
                    areReplyCommentLiked = postsUiState.areReplyCommentsLiked,
                    replyCommentsLikes = postsUiState.replyCommentsLikes,
                    onLike = { postsViewModel.likeComment(it) },
                    onUnlike = { postsViewModel.unlikeComment(it) },
                    onReply = { username, commentId ->
                        commentMode = CommentMode.Reply(username, commentId)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomStart)
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                if (commentMode is CommentMode.Reply) {
                    ReplyIndicator(
                        replyingToUsername = (commentMode as CommentMode.Reply).username,
                        onClose = { commentMode = CommentMode.Comment },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(LocalDimension.current.medium)
                    )
                }
                CommentFooter(
                    userPreferences = userPreferences,
                    author = postsUiState.author!!,
                    commentContent = commentContent,
                    commentMode = commentMode,
                    onCommentChange = { commentContent = it },
                    onComment = {
                        if (commentMode is CommentMode.Comment) {
                            postsViewModel.commentOnPost(postsUiState.post!!._id, commentContent)
                        } else {
                            postsViewModel.replyToComment(
                                postsUiState.post!!._id,
                                (commentMode as CommentMode.Reply).commentId,
                                commentContent
                            )
                        }
                        commentContent = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}

@Composable
fun CommentsTopBar(
    onBack: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = LocalDimension.current.fourExtraLarge,
            alignment = Alignment.Start
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sixExtraLarge)
            .padding(horizontal = LocalDimension.current.medium)
    ) {
        BackIconButton(
            onClick = onBack,
            modifier = Modifier
                .weight(0.1f)
                .wrapContentHeight()
        )
        Text(
            text = stringResource(R.string.comments),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .weight(0.9f)
                .wrapContentHeight()
        )
    }
}

@Composable
fun Caption(
    author: InstaCloneUser,
    post: InstaClonePost,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
                    createdAt = post.createdAt,
                    isEdited = post.isEdited,
                    onUsernameClick = {},
                    modifier = Modifier.wrapContentSize()
                )
                Text(
                    text = post.caption,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        FeedDivider()
    }
}

@Composable
fun ReplyIndicator(
    replyingToUsername: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.replying_to, replyingToUsername),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.weight(0.95f)
        )
        CloseIconButton(
            onClick = onClose,
            modifier = Modifier
                .weight(0.05f)
                .size(LocalDimension.current.small)
        )
    }
}

@Composable
fun CommentFooter(
    userPreferences: UserPreferences,
    author: InstaCloneUser,
    commentContent: String,
    commentMode: CommentMode,
    onCommentChange: (String) -> Unit,
    onComment: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.small),
        modifier = modifier
    ) {
        CommentTextField(
            value = commentContent,
            onValueChange = { onCommentChange(it) },
            label =
            if (commentMode is CommentMode.Comment)
                stringResource(R.string.add_a_comment_for, author.username)
            else
                stringResource(R.string.replying_as, author.username),
            profilePicPath = userPreferences.profilePicPath,
            modifier = Modifier
                .weight(0.85f)
                .wrapContentHeight()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.15f)
                .wrapContentHeight()
        ) {
            TransparentButton(
                text = stringResource(R.string.post),
                isEnabled = commentContent.isNotEmpty(),
                onClick = onComment,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}