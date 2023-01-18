package myapp.hoang.core_ui.components

import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.models.EditImageTab
import myapp.hoang.core_ui.components.models.ProfileTab
import myapp.hoang.core_ui.components.pager.pagerTabIndicatorOffset

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileTabRow(
    tabs: List<ProfileTab>,
    pagerState: PagerState,
    onScrollToPage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        indicator = @Composable { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = MaterialTheme.colorScheme.onSurface,
                height = LocalDimension.current.unit
            )
        },
        modifier = modifier
    ) {
        tabs.forEachIndexed { i, tab ->
            ProfileTab(
                selected = pagerState.currentPage == i,
                icon = tab.icon,
                onClick = { onScrollToPage(i) }
            )
        }
    }
}

@Composable
fun ProfileTab(
    selected: Boolean,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        icon = icon,
        selectedContentColor = MaterialTheme.colorScheme.onSurface,
        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun EditImageTabRow(
    tabs: List<EditImageTab>,
    selectedTabIndex: Int,
    onSwitchTab: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        indicator = @Composable { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = MaterialTheme.colorScheme.onSurface,
                height = LocalDimension.current.twoExtraSmall
            )
        },
        modifier = modifier
    ) {
        tabs.forEachIndexed { i, tab ->
            EditImageTab(
                selected = selectedTabIndex == i,
                text = tab.text,
                onClick = { onSwitchTab(i) }
            )
        }
    }
}

@Composable
fun EditImageTab(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Tab(
        selected = selected,
        onClick = onClick,
        text = {
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.labelMedium
            )
        },
        selectedContentColor = MaterialTheme.colorScheme.onSurface,
        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}