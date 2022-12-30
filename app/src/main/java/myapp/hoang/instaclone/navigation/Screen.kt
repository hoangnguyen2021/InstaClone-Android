package myapp.hoang.instaclone.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object SignupByPhoneScreen : Screen("signup/phone")
    object SignupByEmailScreen : Screen("signup/email")
    object ConfirmationCodeScreen : Screen("confirmation-code")
    object FullNameScreen : Screen("full-name")
    object PasswordScreen : Screen("password")
    object SaveLoginInfoScreen : Screen("save-login-info")
    object BirthdayScreen : Screen("birthday")
    object UsernameScreen : Screen("username")
    object PolicyScreen : Screen("policy")
    object ProfilePictureScreen : Screen("profile-picture")
    object WelcomeScreen : Screen("welcome")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}