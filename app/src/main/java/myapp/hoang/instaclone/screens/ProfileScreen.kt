package myapp.hoang.instaclone.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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

val tabs = listOf(
    ProfileTab(
        icon = { GridIcon(modifier = Modifier.size(LocalDimension.current.mediumLarge)) },
        emptyContent = { GridTabEmptyContent() }
    ),
    ProfileTab(
        icon = { TagIcon(modifier = Modifier.size(LocalDimension.current.extraLarge)) },
        emptyContent = { TagTabEmptyContent() }
    )
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen(
    onProfileUsernameClick: () -> Unit
) {
    var isDiscoverPeopleChecked by remember {
        mutableStateOf(false)
    }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

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
                .height(LocalDimension.current.sixExtraLarge)
                .padding(
                    horizontal = LocalDimension.current.medium
                )
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
        Spacer(modifier = Modifier.height(LocalDimension.current.medium))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.tenExtraLarge)
                .padding(horizontal = LocalDimension.current.medium)
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
            modifier = Modifier
                .padding(horizontal = LocalDimension.current.medium)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(LocalDimension.current.mediumLarge))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.twoExtraLarge)
                .padding(
                    horizontal = LocalDimension.current.medium
                )
        ) {
            SecondaryButton(
                text = stringResource(R.string.edit_profile),
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(LocalDimension.current.extraSmall))
            ToggleDiscoverPeopleIconButton(
                checked = isDiscoverPeopleChecked,
                onCheckedChange = { isDiscoverPeopleChecked = it },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(LocalDimension.current.large))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            ProfileTabRow(
                tabs = tabs,
                pagerState = pagerState,
                onScrollToPage = { scope.launch { pagerState.animateScrollToPage(it) } },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimension.current.fiveExtraLarge)
            )
            HorizontalPager(
                count = tabs.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                tabs[page].emptyContent()
            }
        }
    }
}

@Composable
fun GridTabEmptyContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LocalDimension.current.sevenExtraLarge)
    ) {
        Text(
            text = stringResource(R.string.profile_tab_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(LocalDimension.current.small))
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
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LocalDimension.current.sevenExtraLarge)
    ) {
        Text(
            text = stringResource(R.string.tag_tab_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(LocalDimension.current.small))
        Text(
            text = stringResource(R.string.tag_tab_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}

