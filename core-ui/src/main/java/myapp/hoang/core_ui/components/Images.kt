package myapp.hoang.core_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.R

@Composable
fun OnBoardingProfilePicture(
    imageUri: Any?
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUri)
            .crossfade(true)
            .build(),
        error = painterResource(id = R.drawable.profile_pic_placeholder),
        contentDescription = "Profile picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .aspectRatio(1f)
            .border(
                BorderStroke(5.dp, LightGray),
                CircleShape
            )
            .padding(LocalDimension.current.twoExtraSmall)
            .clip(CircleShape)
    )
}

@Composable
fun OnBoardingProfilePicture2(
    imageUri: Any?
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUri)
            .crossfade(true)
            .build(),
        error = painterResource(id = R.drawable.profile_pic_placeholder),
        contentDescription = "Profile picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(1f)
            .border(
                BorderStroke(0.5.dp, MaterialTheme.colorScheme.onSecondary),
                CircleShape
            )
            .padding(LocalDimension.current.twoExtraSmall)
            .clip(CircleShape)
    )
}

@Composable
fun ProfilePic(
    size: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.profile_pic_placeholder)
            .crossfade(true)
            .build(),
        contentDescription = "Profile pic",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    )
}

@Composable
fun BottomAppBarProfilePic(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.profile_pic_placeholder)
            .crossfade(true)
            .build(),
        contentDescription = "Profile pic",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(LocalDimension.current.extraLarge)
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    )
}