package myapp.hoang.instaclone.screens.comments

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import myapp.hoang.core.utils.toRelativeTimeString
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.BackIconButton
import myapp.hoang.core_ui.components.FeedDivider
import myapp.hoang.core_ui.components.ProfilePic
import myapp.hoang.instaclone.R
import myapp.hoang.instaclone.features.users.viewmodels.InstaCloneUsersViewModel
import myapp.hoang.media.models.InstaClonePost
import myapp.hoang.media.viewmodels.InstaClonePostsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CommentsScreen(
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
                    modifier = Modifier.weight(0.1f)
                )
                Text(
                    text = stringResource(R.string.comments),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.weight(0.9f)
                )
            }
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