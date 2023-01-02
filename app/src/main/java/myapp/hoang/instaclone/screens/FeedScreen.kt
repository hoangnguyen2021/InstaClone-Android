package myapp.hoang.instaclone.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import myapp.hoang.core_ui.Black
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.AddStoryIconButton
import myapp.hoang.core_ui.components.StoryProfilePic

@Composable
fun FeedScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AddStory(
            onClick = {}
        )
    }
}

@Composable
fun AddStory(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentWidth()
            .height(LocalDimension.current.eightExtraLarge)
            .border(LocalDimension.current.unit, Black)
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
}