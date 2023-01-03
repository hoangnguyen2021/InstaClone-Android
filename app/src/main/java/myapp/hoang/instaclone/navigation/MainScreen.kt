package myapp.hoang.instaclone.navigation

sealed class MainScreen(val route: String) {
    object FeedScreen : MainScreen("feed")
    object SearchScreen : MainScreen("search")
    object ReelsScreen : MainScreen("reels")
    object ShopScreen : MainScreen("shop")
    object ProfileScreen : MainScreen("profile")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}