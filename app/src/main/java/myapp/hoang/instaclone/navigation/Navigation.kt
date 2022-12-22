package myapp.hoang.instaclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import myapp.hoang.onboarding.login.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SignupScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen()
        }
    }
}