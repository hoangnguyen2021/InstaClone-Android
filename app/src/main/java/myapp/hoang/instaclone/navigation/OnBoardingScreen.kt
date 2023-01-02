package myapp.hoang.instaclone.navigation

sealed class OnBoardingScreen(val route: String) {
    object LoginScreen : OnBoardingScreen("login")
    object SignupByPhoneScreen : OnBoardingScreen("signup/phone")
    object SignupByEmailScreen : OnBoardingScreen("signup/email")
    object ConfirmationCodeScreen : OnBoardingScreen("confirmation-code")
    object FullNameScreen : OnBoardingScreen("full-name")
    object PasswordScreen : OnBoardingScreen("password")
    object SaveLoginInfoScreen : OnBoardingScreen("save-login-info")
    object BirthdayScreen : OnBoardingScreen("birthday")
    object UsernameScreen : OnBoardingScreen("username")
    object PolicyScreen : OnBoardingScreen("policy")
    object ProfilePictureScreen : OnBoardingScreen("profile-picture")
    object WelcomeScreen : OnBoardingScreen("welcome")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}