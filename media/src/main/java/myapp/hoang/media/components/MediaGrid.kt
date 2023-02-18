package myapp.hoang.media.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import coil.request.videoFrameMillis
import myapp.hoang.core.utils.ServiceUtils
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.White
import myapp.hoang.media.models.*
import myapp.hoang.media.viewmodels.SelectedMedia

@Composable
fun SelectMediaGrid(
    mediaList: List<Media>,
    selectMediaMode: SelectMediaMode,
    selectedMediaList: List<SelectedMedia>,
    focusedMediaIndex: Int,
    onMediaSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 4),
        verticalArrangement = Arrangement.spacedBy(LocalDimension.current.unit),
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.unit),
        modifier = modifier
    ) {
        itemsIndexed(
            items = mediaList
        ) { i, item ->
            when (item) {
                is Image -> {
                    ImagePreview(
                        uri = item.contentUri,
                        selectMediaMode = selectMediaMode,
                        selectedOrder = selectedMediaList.indexOfFirst { it.index == i },
                        isFocused = focusedMediaIndex == i,
                        onClick = { onMediaSelect(i) }
                    )
                }
                is Video -> {
                    VideoPreview(
                        uri = item.contentUri,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Composable
fun ImagePreview(
    uri: Uri,
    selectMediaMode: SelectMediaMode,
    selectedOrder: Int,
    isFocused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(uri)
                .crossfade(true)
                .build(),
            contentDescription = "Image preview",
            contentScale = ContentScale.Crop,
            colorFilter = if (isFocused)
                ColorFilter.tint(
                    color = Color(0xAAFFFFFF),
                    blendMode = BlendMode.Lighten
                ) else null,
            modifier = modifier
                .wrapContentSize()
                .aspectRatio(1f)
                .clip(RectangleShape)
                .clickable(onClick = onClick)
        )
        if (selectMediaMode == SelectMediaMode.MULTIPLE) {
            MultipleMediaIndicator(
                selectedOrder = selectedOrder,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(LocalDimension.current.extraSmall)
            )
        }
    }
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
            .videoFrameMillis(0L)
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

@Composable
fun MultipleMediaIndicator(
    selectedOrder: Int,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(LocalDimension.current.large)
            .clip(CircleShape)
            .background(
                if (selectedOrder != -1) MaterialTheme.colorScheme.primaryContainer
                else White.copy(alpha = 0.2f)
            )
            .border(
                width = LocalDimension.current.unit,
                color = White,
                shape = CircleShape
            )
    ) {
        if (selectedOrder != -1) {
            Text(
                text = selectedOrder.plus(1).toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun PostsGrid(
    posts: List<InstaClonePost>,
    onPostSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        verticalArrangement = Arrangement.spacedBy(LocalDimension.current.twoExtraSmall),
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.twoExtraSmall),
        modifier = modifier
    ) {
        itemsIndexed(items = posts) { i, item ->
            PostPreview(
                mediaPath = item.mediaPaths.first(),
                onClick = { onPostSelect(item._id) }
            )
        }
    }
}

@Composable
fun PostPreview(
    mediaPath: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val previewUrl = ServiceUtils.buildAmazonS3ObjectUrl(mediaPath)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(previewUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Post preview",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .wrapContentSize()
                .aspectRatio(1f)
                .clip(RectangleShape)
                .clickable(onClick = onClick)
        )
    }
}