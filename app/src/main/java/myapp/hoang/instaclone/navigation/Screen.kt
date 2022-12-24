package myapp.hoang.instaclone.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object SignupByPhoneScreen : Screen("signup/phone")
    object SignupByEmailScreen : Screen("signup/email")
    object ConfirmationCodeScreen : Screen("confirmation-code")
    object FullNameScreen : Screen("full-name")
    object PasswordScreen : Screen("password")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}