package myapp.hoang.media.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.MoreIconButton
import myapp.hoang.core_ui.components.ProfilePic
import myapp.hoang.core_ui.components.UsernameAndCaption
import myapp.hoang.media.models.InstaClonePost

@Composable
fun InstaClonePosts(
    posts: List<InstaClonePost>,
    authorUsername: String,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        items(items = posts) { post ->
            InstaClonePost(
                post = post,
                authorUsername = authorUsername,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InstaClonePost(
    post: InstaClonePost,
    authorUsername: String,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = LocalDimension.current.mediumSmall,
                alignment = Alignment.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.1f)
            ) {
                ProfilePic(
                    onClick = {},
                    modifier = Modifier.fillMaxSize(0.8f)
                )
            }
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.8f)
            ) {
                Text(
                    text = authorUsername,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.1f)
            ) {
                MoreIconButton(
                    onClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            PostSlideshow(
                mediaPaths = post.mediaPaths,
                pagerState = pagerState
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(LocalDimension.current.small)
        ) {
            UsernameAndCaption(
                username = authorUsername,
                caption = post.caption,
                onUsernameClick = { }
            )
        }
    }
}