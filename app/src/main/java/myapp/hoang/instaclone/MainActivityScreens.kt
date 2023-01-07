package myapp.hoang.instaclone

import myapp.hoang.instaclone.models.MainActivityScreen
import myapp.hoang.instaclone.screens.CreateContentScreen
import myapp.hoang.instaclone.screens.MainScreen

val mainActivityScreens = listOf(
    MainActivityScreen(
        content = { CreateContentScreen() }
    ),
    MainActivityScreen(
        content = { MainScreen() }
    )
)