package myapp.hoang.core.navigation

sealed class CreateContentScreen(val route: String) {
    object SelectContentScreen : CreateContentScreen("select-content")
    object EditImageScreen : CreateContentScreen("edit-image")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}