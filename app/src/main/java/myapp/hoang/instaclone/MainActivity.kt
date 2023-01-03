package myapp.hoang.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.Black
import myapp.hoang.core_ui.InstaCloneTheme
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawer
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerState
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerValue
import myapp.hoang.core_ui.components.bottomsheet.rememberBottomDrawerState
import myapp.hoang.core_ui.components.InstaCloneBottomAppBar
import myapp.hoang.core_ui.components.MainScreenDrawer
import myapp.hoang.core.navigation.MainScreen
import myapp.hoang.instaclone.screens.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InstaCloneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
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
                            drawerState = drawerState,
                            scope = scope,
                            onCurrentDrawerChange = { currentDrawer = it }
                        )
                    }
                }
            }
        }
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