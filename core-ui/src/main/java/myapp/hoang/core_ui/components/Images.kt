package myapp.hoang.core_ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import myapp.hoang.core.utils.ServiceUtils
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.R
import myapp.hoang.core_ui.utils.applyIf

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
            .wrapContentHeight()
            .aspectRatio(1f)
            .border(
                BorderStroke(5.dp, Light),
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
            .wrapContentHeight()
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
fun ProfilePic(
    path: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val data: Any =
        if (path == null) R.drawable.profile_pic_placeholder
        else ServiceUtils.buildAmazonS3ObjectUrl(path)

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .crossfade(true)
            .build(),
        contentDescription = "Profile pic",
        contentScale = ContentScale.Crop,
        modifier = modifier
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
            .applyIf(isSelected) {
                border(
                    width = LocalDimension.current.twoExtraSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape
                )
            }
            .clip(CircleShape)
            .clickable(onClick = onClick)
    )
}

@Composable
fun ImageEditPreview(
    bitmap: Bitmap,
    modifier: Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(bitmap)
            .crossfade(true)
            .build(),
        contentDescription = "Image edit preview",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun ImageEditPreview(
    bitmap: ImageBitmap,
    modifier: Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(bitmap.asAndroidBitmap())
            .crossfade(true)
            .build(),
        contentDescription = "Image edit preview",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun ImagePreviewPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF363636))
    )
}

@Composable
fun ImagesEditPreview(
    bitmaps: List<ImageBitmap>,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.small),
        contentPadding = PaddingValues(
            horizontal = LocalDimension.current.medium
        ),
        modifier = modifier
    ) {
        itemsIndexed(items = bitmaps) { index, bitmap ->
            ImageEditPreview(
                bitmap = bitmap,
                modifier = modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(RectangleShape)
                    .clickable { onImageClick(index) }
            )
        }
    }
}

@Composable
fun PostImage(
    path: String,
    modifier: Modifier = Modifier
) {
    val url = ServiceUtils.buildAmazonS3ObjectUrl(path)

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "Post image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .aspectRatio(1f)
            .clip(RectangleShape)
    )
}