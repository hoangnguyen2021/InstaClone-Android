package myapp.hoang.core.navigation

sealed class MainScreen(val route: String) {
    object FeedScreen : MainScreen("feed")
    object SearchScreen : MainScreen("search")
    object ReelsScreen : MainScreen("reels")
    object ShopScreen : MainScreen("shop")
    object ProfileScreen : MainScreen("profile")
    object PostsScreen : MainScreen("posts")
    object CommentsScreen : MainScreen("comments") {
        const val POST_ID = "post-id"
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}