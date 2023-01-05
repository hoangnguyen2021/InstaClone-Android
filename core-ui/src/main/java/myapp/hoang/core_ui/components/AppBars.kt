package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import myapp.hoang.core.navigation.MainScreen
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstaCloneTopAppBar() {
    TopAppBar(
        title = {
            InstaCloneBrand(
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(vertical = LocalDimension.current.small)
                    .offset(x = (-10).dp)
            )
        },
        actions = {
            NewPostIconButton(onClick = {})
            HeartIconButton(onClick = {})
            MessageIconButton(onClick = {})
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sixExtraLarge)
    )
}

@Composable
fun InstaCloneBottomAppBar(
    currentDestination: NavDestination?,
    onClick: (MainScreen) -> Unit
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = LocalDimension.current.zero,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sixExtraLarge)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            FeedDivider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                HomeIconButton(
                    isSelected = currentDestination?.hierarchy?.any { it.route == MainScreen.FeedScreen.route } == true,
                    onClick = { onClick(MainScreen.FeedScreen) }
                )
                SearchIconButton(
                    isSelected = currentDestination?.hierarchy?.any { it.route == MainScreen.SearchScreen.route } == true,
                    onClick = { onClick(MainScreen.SearchScreen) }
                )
                ReelsIconButton(
                    isSelected = currentDestination?.hierarchy?.any { it.route == MainScreen.ReelsScreen.route } == true,
                    onClick = { onClick(MainScreen.ReelsScreen) }
                )
                ShopIconButton(
                    isSelected = currentDestination?.hierarchy?.any { it.route == MainScreen.ShopScreen.route } == true,
                    onClick = { onClick(MainScreen.ShopScreen) }
                )
                BottomAppBarProfilePic(
                    isSelected = currentDestination?.hierarchy?.any { it.route == MainScreen.ProfileScreen.route } == true,
                    onClick = { onClick(MainScreen.ProfileScreen) }
                )
            }
        }
    }
}
