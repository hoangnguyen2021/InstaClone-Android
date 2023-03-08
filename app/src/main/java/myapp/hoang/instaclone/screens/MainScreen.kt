package myapp.hoang.instaclone.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import myapp.hoang.core.navigation.MainScreen
import myapp.hoang.core.navigation.MainScreen.CommentsScreen.POST_ID
import myapp.hoang.core.navigation.MainScreen.PostsScreen.POST_INDEX
import myapp.hoang.core_ui.Black
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.InstaCloneBottomAppBar
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawer
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerState
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerValue
import myapp.hoang.core_ui.components.bottomsheet.rememberBottomDrawerState
import myapp.hoang.core_ui.components.models.MainScreenDrawer
import myapp.hoang.instaclone.screens.comments.CommentsScreen
import myapp.hoang.instaclone.screens.posts.PostsScreen
import myapp.hoang.media.viewmodels.InstaClonePostsViewModel
import myapp.hoang.settings.models.UserPreferences

@Composable
fun MainScreen(
    userPreferences: UserPreferences
) {
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
            userPreferences = userPreferences,
            drawerState = drawerState,
            scope = scope,
            onCurrentDrawerChange = { currentDrawer = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    userPreferences: UserPreferences,
    drawerState: BottomDrawerState,
    scope: CoroutineScope,
    onCurrentDrawerChange: (MainScreenDrawer) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val postsViewModel = hiltViewModel<InstaClonePostsViewModel>()

    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = MainScreen.FeedScreen.route
                ) {
                    composable(route = MainScreen.FeedScreen.route) {
                        FeedScreen()
                    }
                    composable(route = MainScreen.SearchScreen.route) {
                        SearchScreen()
                    }
                    composable(route = MainScreen.ReelsScreen.route) {
                        ReelsScreen()
                    }
                    composable(route = MainScreen.ShopScreen.route) {
                        ShopScreen()
                    }
                    composable(route = MainScreen.ProfileScreen.route) {
                        ProfileScreen(
                            postsViewModel = postsViewModel,
                            onProfileUsernameClick = {
                                onCurrentDrawerChange(MainScreenDrawer.SelectAccountDrawer)
                                scope.launch {
                                    drawerState.expand()
                                }
                            },
                            onPostClick = { _, index ->
                                navController.navigate(MainScreen.PostsScreen.withArgs(index.toString()))
                            }
                        )
                    }
                    composable(
                        route = "${MainScreen.PostsScreen.route}/{$POST_INDEX}",
                        arguments = listOf(
                            navArgument(POST_INDEX) {
                                type = NavType.IntType
                                defaultValue = 0
                                nullable = false
                            }
                        )
                    ) { entry ->
                        val postIndex = entry.arguments?.getInt(POST_INDEX, 0) ?: 0
                        PostsScreen(
                            postsViewModel = postsViewModel,
                            postIndex = postIndex,
                            onBack = { navController.navigateUp() },
                            onComment = {
                                navController.navigate(MainScreen.CommentsScreen.withArgs(it))
                            }
                        )
                    }
                    composable(
                        route = "${MainScreen.CommentsScreen.route}/{$POST_ID}",
                        arguments = listOf(
                            navArgument(POST_ID) {
                                type = NavType.StringType
                                defaultValue = ""
                                nullable = false
                            }
                        )
                    ) { entry ->
                        val postId = entry.arguments?.getString(POST_ID, "") ?: ""
                        CommentsScreen(
                            userPreferences = userPreferences,
                            postsViewModel = postsViewModel,
                            postId = postId,
                            onBack = { navController.navigateUp() }
                        )
                    }
                }
            }
        },
        bottomBar = {
            if (currentDestination?.route?.contains(MainScreen.CommentsScreen.route) == false) {
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
        }
    )
}