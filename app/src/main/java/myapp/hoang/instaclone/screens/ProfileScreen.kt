package myapp.hoang.instaclone.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.GridIcon
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.TagIcon
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.models.ProfileTab
import myapp.hoang.instaclone.R
import myapp.hoang.media.viewmodels.InstaClonePostsViewModel

val profileTabs = listOf(
    ProfileTab(
        icon = { GridIcon(modifier = Modifier.size(LocalDimension.current.mediumLarge)) },
        emptyContent = { GridTabEmptyContent() }
    ),
    ProfileTab(
        icon = { TagIcon(modifier = Modifier.size(LocalDimension.current.extraLarge)) },
        emptyContent = { TagTabEmptyContent() }
    )
)

@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalLifecycleComposeApi::class
)
@Composable
fun ProfileScreen(
    onProfileUsernameClick: () -> Unit
) {
    var isDiscoverPeopleChecked by remember {
        mutableStateOf(false)
    }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val postsViewModel = hiltViewModel<InstaClonePostsViewModel>()

    val uiState by postsViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        postsViewModel.getAllPostsByUser()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.08f)
                .padding(horizontal = LocalDimension.current.medium)
        ) {
            ProfileUsername(
                username = "username",
                onClick = onProfileUsernameClick
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
            ) {
                NewPostIconButton(onClick = {})
                MenuIconButton(onClick = {})
            }
        }
        FeedDivider()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.28f)
                .padding(
                    top = LocalDimension.current.medium,
                    bottom = LocalDimension.current.mediumLarge,
                    start = LocalDimension.current.medium,
                    end = LocalDimension.current.medium
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimension.current.tenExtraLarge)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    ProfilePic(
                        size = LocalDimension.current.nineExtraLarge,
                        onClick = { }
                    )
                    ProfileStat(value = 0, unit = "Posts")
                    ProfileStat(value = 0, unit = "Followers")
                    ProfileStat(value = 0, unit = "Following")
                }
            }
            Text(
                text = "Display Name",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(LocalDimension.current.mediumLarge))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = LocalDimension.current.extraSmall,
                    alignment = Alignment.Start
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimension.current.twoExtraLarge)
            ) {
                SecondaryButton(
                    text = stringResource(R.string.edit_profile),
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight()
                )
                ToggleDiscoverPeopleIconButton(
                    checked = isDiscoverPeopleChecked,
                    onCheckedChange = { isDiscoverPeopleChecked = it },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.64f)
        ) {
            ProfileTabRow(
                tabs = profileTabs,
                pagerState = pagerState,
                onScrollToPage = { scope.launch { pagerState.animateScrollToPage(it) } },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimension.current.fiveExtraLarge)
            )
            HorizontalPager(
                count = profileTabs.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                profileTabs[page].emptyContent()
            }
        }
    }
}

@Composable
fun GridTabEmptyContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = LocalDimension.current.small,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LocalDimension.current.sevenExtraLarge)
    ) {
        Text(
            text = stringResource(R.string.profile_tab_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = stringResource(R.string.profile_tab_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TagTabEmptyContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = LocalDimension.current.small,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LocalDimension.current.sevenExtraLarge)
    ) {
        Text(
            text = stringResource(R.string.tag_tab_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = stringResource(R.string.tag_tab_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}

