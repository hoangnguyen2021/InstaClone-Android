package myapp.hoang.instaclone.screens.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core.models.InstaCloneUser
import myapp.hoang.settings.models.UserPreferences
import myapp.hoang.core.utils.toRelativeTimeString
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.BackIconButton
import myapp.hoang.core_ui.components.CommentTextField
import myapp.hoang.core_ui.components.FeedDivider
import myapp.hoang.core_ui.components.ProfilePic
import myapp.hoang.instaclone.R
import myapp.hoang.instaclone.features.users.viewmodels.InstaCloneUsersViewModel
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
    val usersViewModel = hiltViewModel<InstaCloneUsersViewModel>()

    val postsUiState by postsViewModel.uiState.collectAsStateWithLifecycle()
    val usersUiState by usersViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        postsViewModel.getPost(postId)
    }

    LaunchedEffect(key1 = postsUiState.post) {
        postsUiState.post?.let {
            usersViewModel.getUserById(it.authorId)
        }
    }

    if (usersUiState.user != null && postsUiState.post != null) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
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
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Caption(
                        author = usersUiState.user!!,
                        post = postsUiState.post!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(
                                vertical = LocalDimension.current.small,
                                horizontal = LocalDimension.current.medium
                            )
                    )
                    FeedDivider()
                }
            }
            CommentFooter(
                userPreferences = userPreferences,
                author = usersUiState.user!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomStart)
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    }
}

@Composable
fun Caption(
    author: InstaCloneUser,
    post: InstaClonePost,
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
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(author.username)
                    }
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(post.createdAt.toRelativeTimeString())
                        if (post.isEdited) {
                            append(" • ")
                            append("Edited")
                        }
                    }
                },
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = post.caption,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun CommentFooter(
    userPreferences: UserPreferences,
    author: InstaCloneUser,
    modifier: Modifier = Modifier
) {
    var comment by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.small),
        modifier = modifier
    ) {
        CommentTextField(
            value = comment,
            onValueChange = { comment = it },
            label = "Add a comment for ${author.username}...",
            profilePicPath = userPreferences.profilePicPath,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight()
        )
    }
}