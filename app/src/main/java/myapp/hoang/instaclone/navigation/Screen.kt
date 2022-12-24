package myapp.hoang.instaclone.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object SignupByPhoneScreen : Screen("signup/phone")
    object SignupByEmailScreen : Screen("signup/email")
    object ConfirmationCodeScreen : Screen("confirmation-code")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}