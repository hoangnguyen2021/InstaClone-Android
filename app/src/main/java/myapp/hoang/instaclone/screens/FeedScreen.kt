package myapp.hoang.instaclone.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.AddStoryIconButton
import myapp.hoang.core_ui.components.FeedDivider
import myapp.hoang.instaclone.components.InstaCloneTopAppBar
import myapp.hoang.core_ui.components.StoryProfilePic

@Composable
fun FeedScreen() {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        InstaCloneTopAppBar()
        FeedDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.twelveExtraLarge)
        ) {
            AddStory(
                onClick = {}
            )
        }
        FeedDivider()
    }
}

@Composable
fun AddStory(
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .padding(horizontal = LocalDimension.current.small)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .wrapContentSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.align(Alignment.Center)
            ) {
                StoryProfilePic(onClick = onClick)
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(LocalDimension.current.extraSmall)
                    .size(LocalDimension.current.mediumLarge)
            ) {
                AddStoryIconButton(onClick = onClick)
            }
        }
        Text(
            text = "Your story",
            style = MaterialTheme.typography.labelSmall
        )
    }
}