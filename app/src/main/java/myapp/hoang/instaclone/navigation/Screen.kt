package myapp.hoang.instaclone.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object SignupByPhoneScreen : Screen("signup/phone")
    object SignupByEmailScreen : Screen("signup/email")
}