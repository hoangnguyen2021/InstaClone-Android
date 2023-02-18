package myapp.hoang.instaclone.screens.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.BackIconButton
import myapp.hoang.instaclone.R
import myapp.hoang.media.components.InstaClonePosts
import myapp.hoang.media.viewmodels.InstaClonePostsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PostsScreen(
    postsViewModel: InstaClonePostsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState by postsViewModel.uiState.collectAsStateWithLifecycle()

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
                text = stringResource(R.string.posts),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(0.9f)
            )
        }
        InstaClonePosts(
            posts = uiState.posts,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}