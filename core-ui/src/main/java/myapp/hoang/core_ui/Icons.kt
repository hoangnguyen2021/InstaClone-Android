package myapp.hoang.core_ui

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        painter = painterResource(id = R.drawable.ic_heart_notifications),
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
        painter = painterResource(id = R.drawable.ic_home_outline),
        contentDescription = "Home"
    )
}

@Composable
fun HomeSelectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_home_fill),
        contentDescription = "Home"
    )
}

@Composable
fun SearchUnselectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_search_outline),
        contentDescription = "Search"
    )
}

@Composable
fun SearchSelectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_search_fill),
        contentDescription = "Search"
    )
}

@Composable
fun ReelsUnselectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_reels_outline),
        contentDescription = "Reels"
    )
}

@Composable
fun ReelsSelectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_reels_fill),
        contentDescription = "Reels"
    )
}

@Composable
fun ShopUnselectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_shop_outline),
        contentDescription = "Shop"
    )
}

@Composable
fun ShopSelectedIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_shop_fill),
        contentDescription = "Shop"
    )
}

@Composable
fun SaveIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_save_outline),
        contentDescription = "Save"
    )
}

@Composable
fun UnsaveIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_save_fill),
        contentDescription = "Unsave"
    )
}

@Composable
fun LikeIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_heart_outline),
        contentDescription = "Like"
    )
}

@Composable
fun UnlikeIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_heart_fill),
        contentDescription = "Unlike"
    )
}

@Composable
fun CancelIcon(color: Color, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.ic_cancel),
        contentDescription = "Like",
        modifier = modifier,
        tint = color
    )
}

@Composable
fun PasswordHiddenIcon(color: Color, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.ic_password_hidden),
        contentDescription = "Hide password",
        modifier = modifier,
        tint = color
    )
}

@Composable
fun PasswordShownIcon(color: Color, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.ic_password_shown),
        contentDescription = "Show password",
        modifier = modifier,
        tint = color
    )
}