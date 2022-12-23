package myapp.hoang.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.instaclone.navigation.Screen
import myapp.hoang.onboarding.login.LoginScreen
import myapp.hoang.onboarding.signup.SignupScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            OnBoardingTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen {
                                navController.navigate(Screen.SignupScreen.route)
                            }
                        }
                        composable(route = Screen.SignupScreen.route) {
                            SignupScreen {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}