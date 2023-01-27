package myapp.hoang.core.navigation

sealed class CreateContentScreen(val route: String) {
    object SelectMediaScreen : CreateContentScreen("select-media")
    object EditImageScreen : CreateContentScreen("edit-image") {
        const val INDEX = "index"
    }
    object EditImagesScreen : CreateContentScreen("edit-images")
    object WritePostScreen : CreateContentScreen("write-post")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}