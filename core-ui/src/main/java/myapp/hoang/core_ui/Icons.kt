package myapp.hoang.core_ui

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun NewPostIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_new_post),
        contentDescription = "New Post"
    )
}

@Composable
fun HeartIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_heart),
        contentDescription = "Notifications"
    )
}

@Composable
fun MessageIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_messenger),
        contentDescription = "Messages"
    )
}

@Composable
fun HomeUnselectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_home_unselected),
        contentDescription = "Home"
    )
}

@Composable
fun HomeSelectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_home_selected),
        contentDescription = "Home"
    )
}