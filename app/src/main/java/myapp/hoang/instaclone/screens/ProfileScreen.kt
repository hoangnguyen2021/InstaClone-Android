package myapp.hoang.instaclone.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.*

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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .padding(horizontal = LocalDimension.current.medium)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight()
            ) {
                ProfilePic(
                    size = LocalDimension.current.nineExtraLarge,
                    onClick = {  }
                )
                Text(
                    text = "Display Name",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                ProfileStat(value = 0, unit = "Posts")
                ProfileStat(value = 0, unit = "Followers")
                ProfileStat(value = 0, unit = "Following")
            }
        }
    }
}

