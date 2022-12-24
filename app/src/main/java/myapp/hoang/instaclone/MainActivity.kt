package myapp.hoang.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import myapp.hoang.core_ui.*
import myapp.hoang.instaclone.navigation.Screen
import myapp.hoang.onboarding.login.LoginScreen
import myapp.hoang.onboarding.signup.*

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
                            SignupByPhoneScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    navController.navigate(Screen.ConfirmationCodeScreen.withArgs(it))
                                },
                                onSignUpWithEmailClick = {
                                    navController.navigate(Screen.SignupByEmailScreen.route)
                                }
                            )
                        }
                        composable(route = Screen.SignupByEmailScreen.route) {
                            SignupByEmailScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    navController.navigate(Screen.ConfirmationCodeScreen.withArgs(it))
                                },
                                onSignUpWithMobileNumberClick = { navController.navigateUp() }
                            )
                        }
                        composable(
                            route = "${Screen.ConfirmationCodeScreen.route}/{username}",
                            arguments = listOf(
                                navArgument("username") {
                                    type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) { entry ->
                            val username = entry.arguments?.getString("username")
                            if (username != null) {
                                ConfirmationCodeScreen(
                                    username = username,
                                    onBackClick = { navController.navigateUp() },
                                    onNextClick = { navController.navigate(Screen.FullNameScreen.route) },
                                    onLoginClick = {
                                        navController.navigateUp()
                                        navController.navigateUp()
                                        if (!username.startsWith("+"))
                                            navController.navigateUp()
                                    }
                                )
                            }
                        }
                        composable(route = Screen.FullNameScreen.route) {
                            FullNameScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { navController.navigate(Screen.PasswordScreen.route) }
                            )
                        }
                        composable(route = Screen.PasswordScreen.route) {
                            PasswordScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { navController.navigate(Screen.SaveLoginInfoScreen.route) }
                            )
                        }
                        composable(route = Screen.SaveLoginInfoScreen.route) {
                            SaveLoginInfoScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {}
                            )
                        }
                    }
                }
            }
        }
    }
}