package myapp.hoang.instaclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.SwipeIndicatorIcon
import myapp.hoang.core_ui.components.FeedDivider
import myapp.hoang.core_ui.components.MenuIconButton
import myapp.hoang.core_ui.components.NewPostIconButton
import myapp.hoang.instaclone.components.ProfileUsername

@Composable
fun ProfileScreen(
    onProfileUsernameClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.sixExtraLarge)
                .padding(
                    horizontal = LocalDimension.current.medium
                )
        ) {
            ProfileUsername(
                username = "username",
                onClick = onProfileUsernameClick
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
            ) {
                NewPostIconButton(onClick = {})
                MenuIconButton(onClick = {})
            }
        }
        FeedDivider()
    }
}

