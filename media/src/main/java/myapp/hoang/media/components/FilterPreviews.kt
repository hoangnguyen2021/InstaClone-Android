package myapp.hoang.media.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.ImageEditPreview
import myapp.hoang.media.models.ImageFilter

@Composable
fun FilterPreviews(
    imageFilterList: List<ImageFilter>,
    filterIndex: Int,
    onFilterSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = filterIndex) {
        lazyListState.animateScrollToItem(filterIndex, -200)
    }

    LazyRow(
        state = lazyListState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.small),
        contentPadding = PaddingValues(
            horizontal = LocalDimension.current.medium
        ),
        modifier = modifier
    ) {
        itemsIndexed(items = imageFilterList) { i, imageFilter ->
            FilterPreview(
                imageFilter = imageFilter,
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight(0.5f)
                    .clickable { onFilterSelect(i) },
                selected = filterIndex == i
            )
        }
    }
}

@Composable
fun FilterPreview(
    imageFilter: ImageFilter,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = LocalDimension.current.small,
            alignment = Alignment.CenterVertically
        ),
        modifier = modifier
    ) {
        Text(
            text = imageFilter.name,
            style = MaterialTheme.typography.labelMedium,
            color = if (selected)
                MaterialTheme.colorScheme.onSurface
            else
                MaterialTheme.colorScheme.onSurfaceVariant
        )
        ImageEditPreview(
            bitmap = imageFilter.filterPreview,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RectangleShape)
        )
    }
}