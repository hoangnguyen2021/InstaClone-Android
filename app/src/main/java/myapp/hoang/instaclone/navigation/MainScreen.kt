package myapp.hoang.instaclone.navigation

sealed class MainScreen(val route: String) {
    object FeedScreen : MainScreen("feed")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}