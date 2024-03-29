package myapp.hoang.instaclone.screens.posts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.BackIconButton
import myapp.hoang.instaclone.R
import myapp.hoang.media.components.InstaClonePosts
import myapp.hoang.media.viewmodels.InstaClonePostsViewModel

@Composable
fun PostsScreen(
    postsViewModel: InstaClonePostsViewModel = hiltViewModel(),
    postIndex: Int,
    onBack: () -> Unit,
    onComment: (String) -> Unit,
) {
    val postsUiState by postsViewModel.uiState.collectAsStateWithLifecycle()

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
                text = stringResource(R.string.posts),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .weight(0.9f)
                    .wrapContentHeight()
            )
        }
        InstaClonePosts(
            posts = postsUiState.posts,
            postIndex = postIndex,
            areLiked = postsUiState.arePostsLiked,
            likes = postsUiState.postsLikes,
            onLike = { postsViewModel.likePost(it) },
            onUnlike = { postsViewModel.unlikePost(it) },
            onComment = onComment,
            modifier = Modifier.fillMaxSize()
        )
    }
}