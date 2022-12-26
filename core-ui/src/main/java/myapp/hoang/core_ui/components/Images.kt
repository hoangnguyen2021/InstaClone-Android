package myapp.hoang.core_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import myapp.hoang.core_ui.LightGray
import myapp.hoang.core_ui.LocalDimension
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