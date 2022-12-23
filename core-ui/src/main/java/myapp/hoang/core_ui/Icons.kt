package myapp.hoang.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

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

@Composable
fun GradientInstagramIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.instagram_logo),
        contentDescription = "Instagram logo",
        modifier = modifier,
        tint = Unspecified
    )
}

@Composable
fun MetaIcon(color: Color, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.logo_meta_platforms_inc_white),
        contentDescription = "Meta logo",
        modifier = modifier,
        tint = color
    )
}

@Preview
@Composable
fun OnBoardingIconsPreview() {
    OnBoardingTheme {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .background(
                        brush = onBoardingBackgroundBrush
                    )
                    .padding(
                        horizontal = LocalDimension.current.small
                    )
            ) {
                GradientInstagramIcon(
                    modifier = Modifier.size(LocalDimension.current.eightExtraLarge)
                )
                MetaIcon(
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(LocalDimension.current.fiveExtraLarge)
                )
            }
        }
    }
}

@Composable
fun BackIcon(color: Color, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_back),
        contentDescription = "Go back",
        modifier = modifier,
        tint = color
    )
}