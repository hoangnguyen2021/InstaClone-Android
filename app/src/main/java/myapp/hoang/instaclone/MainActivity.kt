package myapp.hoang.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import myapp.hoang.core_ui.*
import myapp.hoang.instaclone.navigation.Screen
import myapp.hoang.onboarding.login.LoginScreen
import myapp.hoang.onboarding.signup.SignupByEmailScreen
import myapp.hoang.onboarding.signup.SignupByPhoneScreen

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
                                navController.navigate(Screen.SignupByPhoneScreen.route)
                            }
                        }
                        composable(route = Screen.SignupByPhoneScreen.route) {
                            SignupByPhoneScreen (
                                { navController.navigateUp() },
                                { navController.navigate(Screen.SignupByEmailScreen.route) }
                            )
                        }
                        composable(route = Screen.SignupByEmailScreen.route) {
                            SignupByEmailScreen (
                                { navController.navigateUp() },
                                { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}