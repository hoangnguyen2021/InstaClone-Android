package myapp.hoang.media.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.tek.pagerindicator.DotAnimation
import com.tek.pagerindicator.DotStyle
import com.tek.pagerindicator.PagerIndicator
import myapp.hoang.core.models.InstaCloneUser
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.*
import myapp.hoang.media.models.InstaClonePost

@Composable
fun InstaClonePosts(
    posts: List<InstaClonePost>,
    areLiked: List<Boolean>,
    author: InstaCloneUser,
    onLike: (String) -> Unit,
    onUnlike: (String) -> Unit,
    onComment: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        itemsIndexed(items = posts) {i, post ->
            InstaClonePost(
                post = post,
                isLiked = areLiked[i],
                author = author,
                onLike = onLike,
                onUnlike = onUnlike,
                onComment = onComment,
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
    isLiked: Boolean,
    author: InstaCloneUser,
    onLike: (String) -> Unit,
    onUnlike: (String) -> Unit,
    onComment: (String) -> Unit,
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
                author = author,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimension.current.fiveExtraLarge)
                    .padding(horizontal = LocalDimension.current.mediumSmall)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            PostSlideshow(
                mediaPaths = post.mediaPaths,
                pagerState = pagerState,
                modifier = Modifier.fillMaxSize()
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
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                InstaClonePostFooter(
                    post = post,
                    isLiked = isLiked,
                    onLike = onLike,
                    onUnlike = onUnlike,
                    onComment = onComment,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalDimension.current.fiveExtraLarge)
                )
                if (post.mediaPaths.size > 1) {
                    PagerIndicator(
                        pagerState = pagerState,
                        dotStyle = DotStyle(
                            currentDotRadius = 8f,
                            notLastDotRadius = 2f,
                            regularDotRadius = 7f,
                            dotMargin = 12f,
                            visibleDotCount = 7,
                            currentDotColor = MaterialTheme.colorScheme.primaryContainer,
                            regularDotColor = MaterialTheme.colorScheme.tertiaryContainer
                        ),
                        dotAnimation = DotAnimation.defaultDotAnimation,
                        orientation = Orientation.Vertical,
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(LocalDimension.current.mediumLarge)
                    )
                }
            }
            if (post.likes.isNotEmpty()) {
                Likes(
                    value = post.likes.size,
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
            UsernameAndCaption(
                username = author.username,
                caption = post.caption,
                onUsernameClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
fun InstaClonePostHeader(
    author: InstaCloneUser,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = LocalDimension.current.mediumSmall,
            alignment = Alignment.Start
        ),
        modifier = modifier
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
                fontWeight = FontWeight.Medium
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
fun InstaClonePostFooter(
    post: InstaClonePost,
    isLiked: Boolean,
    onLike: (String) -> Unit,
    onUnlike: (String) -> Unit,
    onComment: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = LocalDimension.current.mediumSmall,
            alignment = Alignment.Start
        ),
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
        ) {
            if (isLiked) {
                UnlikeIconButton(
                    onClick = { onUnlike(post._id) },
                    modifier = Modifier.size(LocalDimension.current.large)
                )
            } else {
                LikeIconButton(
                    onClick = { onLike(post._id) },
                    modifier = Modifier.size(LocalDimension.current.large)
                )
            }

        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
        ) {
            CommentIconButton(
                onClick = { onComment(post._id) },
                modifier = Modifier.size(LocalDimension.current.large)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
        ) {
            SendIconButton(
                onClick = {},
                modifier = Modifier.size(LocalDimension.current.large)
            )
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
            SaveIconButton(
                onClick = {},
                modifier = Modifier.size(22.dp)
            )
        }
    }
}