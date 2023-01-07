package myapp.hoang.instaclone.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.Black
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.InstaCloneBottomAppBar
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawer
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerState
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerValue
import myapp.hoang.core_ui.components.bottomsheet.rememberBottomDrawerState
import myapp.hoang.core_ui.components.models.MainScreenDrawer

@Composable
fun MainScreen() {
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentDrawer by remember {
        mutableStateOf<MainScreenDrawer>(MainScreenDrawer.NoDrawer)
    }

    BottomDrawer(
        drawerContent = currentDrawer.drawerContent,
        drawerState = drawerState,
        drawerShape = RoundedCornerShape(
            topStart = LocalDimension.current.small,
            topEnd = LocalDimension.current.small
        ),
        scrimColor = Black.copy(alpha = 0.5f),
        gesturesEnabled = drawerState.isOpen
    ) {
        MainScreenContent(
            drawerState = drawerState,
            scope = scope,
            onCurrentDrawerChange = { currentDrawer = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    drawerState: BottomDrawerState,
    scope: CoroutineScope,
    onCurrentDrawerChange: (MainScreenDrawer) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = myapp.hoang.core.navigation.MainScreen.FeedScreen.route
                ) {
                    composable(route = myapp.hoang.core.navigation.MainScreen.FeedScreen.route) {
                        FeedScreen()
                    }
                    composable(route = myapp.hoang.core.navigation.MainScreen.SearchScreen.route) {
                        SearchScreen()
                    }
                    composable(route = myapp.hoang.core.navigation.MainScreen.ReelsScreen.route) {
                        ReelsScreen()
                    }
                    composable(route = myapp.hoang.core.navigation.MainScreen.ShopScreen.route) {
                        ShopScreen()
                    }
                    composable(route = myapp.hoang.core.navigation.MainScreen.ProfileScreen.route) {
                        ProfileScreen(
                            onProfileUsernameClick = {
                                onCurrentDrawerChange(MainScreenDrawer.SelectAccountDrawer)
                                scope.launch {
                                    drawerState.expand()
                                }
                            }
                        )
                    }
                }
            }
        },
        bottomBar = {
            InstaCloneBottomAppBar(
                currentDestination = currentDestination,
                onClick = { mainScreen ->
                    navController.navigate(mainScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    )
}