package myapp.hoang.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import myapp.hoang.core_ui.InstaCloneTheme
import myapp.hoang.core_ui.components.*
import myapp.hoang.instaclone.navigation.MainScreen
import myapp.hoang.instaclone.screens.FeedScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InstaCloneTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(
                        topBar = { InstaCloneTopAppBar() },
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
                                }
                            }
                        },
                        bottomBar = { InstaCloneBottomAppBar() }
                    )
                }
            }
        }
    }
}