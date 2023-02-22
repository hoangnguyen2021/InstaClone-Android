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
import myapp.hoang.core.models.InstaCloneUser
import myapp.hoang.core_ui.LikeIcon
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.*
import myapp.hoang.media.models.InstaClonePost

@Composable
fun InstaClonePosts(
    posts: List<InstaClonePost>,
    author: InstaCloneUser,
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
                author = author,
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
    author: InstaCloneUser,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            InstaClonePostHeader(
                author = author
            )
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
                .padding(horizontal = LocalDimension.current.mediumSmall)
        ) {
            InstaClonePostFooter()
            UsernameAndCaption(
                username = author.username,
                caption = post.caption,
                onUsernameClick = { }
            )
        }
    }
}

@Composable
fun InstaClonePostHeader(
    author: InstaCloneUser
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
            .padding(horizontal = LocalDimension.current.mediumSmall)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
        ) {
            ProfilePic(
                path = author.profilePicPath,
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
                text = author.username,
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
}

@Composable
fun InstaClonePostFooter() {
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
            LikeIconButton(onClick = {})
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
        ) {
            CommentIconButton(onClick = {})
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
        ) {
            SendIconButton(onClick = {})
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.6f)
        ) {
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
        ) {

        }
    }

}