package myapp.hoang.instaclone.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object SignupScreen : Screen("signup")
}