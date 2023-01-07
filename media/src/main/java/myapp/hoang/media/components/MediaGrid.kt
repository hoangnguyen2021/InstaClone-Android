package myapp.hoang.media.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video
import java.io.File

@Composable
fun MediaGrid(
    mediaList: List<Media>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 4),
        verticalArrangement = Arrangement.spacedBy(LocalDimension.current.unit),
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.unit),
        modifier = modifier
    ) {
        items(
            items = mediaList
        ) { item ->
            when (item) {
                is Image -> {
                    ImagePreview(
                        uri = item.contentUri,
                        onClick = {}
                    )
                }
                is Video -> {
                    VideoPreview(
                        uri = item.contentUri,
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun ImagePreview(
    uri: Uri,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .crossfade(true)
            .build(),
        contentDescription = "Image preview",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .wrapContentSize()
            .aspectRatio(1f)
            .clip(RectangleShape)
            .clickable(onClick = onClick)
    )
}

@Composable
fun VideoPreview(
    uri: Uri,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .decoderFactory(VideoFrameDecoder.Factory())
            .crossfade(true)
            .build(),
        contentDescription = "Video preview",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .wrapContentSize()
            .aspectRatio(1f)
            .clip(RectangleShape)
            .clickable(onClick = onClick)
    )
}
