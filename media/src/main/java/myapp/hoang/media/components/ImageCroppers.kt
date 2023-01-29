package myapp.hoang.media.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.cropper.ImageCropper
import com.smarttoolfactory.cropper.model.AspectRatio
import com.smarttoolfactory.cropper.model.OutlineType
import com.smarttoolfactory.cropper.model.RectCropShape
import com.smarttoolfactory.cropper.settings.CropDefaults
import com.smarttoolfactory.cropper.settings.CropOutlineProperty
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.media.viewmodels.SelectedMedia

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CropperCarousel(
    selectedMediaList: List<SelectedMedia>,
    onCropSuccess: (Int, ImageBitmap) -> Unit
) {
    val scrollState = rememberScrollState()
    val overscrollEffect = ScrollableDefaults.overscrollEffect()

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.mediumLarge),
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(
                state = scrollState,
                flingBehavior = ScrollableDefaults.flingBehavior()
            )
            .overscroll(overscrollEffect)
    ) {
        selectedMediaList.forEach { selectedMedia ->
            selectedMedia.originalBitmap?.let { originalBitmap ->
                InstaCloneCropper(
                    crop = selectedMedia.crop,
                    onCropStart = {},
                    onCropSuccess = { bitmap -> onCropSuccess(selectedMedia.index, bitmap) },
                    imageBitmap = originalBitmap,
                    modifier = Modifier
                        .fillMaxHeight(if (selectedMediaList.size == 1) 1f else 0.9f)
                        .aspectRatio(1f)
                )
            }
        }
    }
}

@Composable
fun InstaCloneCropper(
    imageBitmap: ImageBitmap,
    crop: Boolean,
    onCropStart: () -> Unit,
    onCropSuccess: (ImageBitmap) -> Unit,
    modifier: Modifier = Modifier
) {
    ImageCropper(
        imageBitmap = imageBitmap,
        contentDescription = "InstaClone Image Cropper",
        cropStyle = CropDefaults.style(
            overlayColor = MaterialTheme.colorScheme.primary,
            handleColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.background
        ),
        cropProperties = CropDefaults.properties(
            cropOutlineProperty = CropOutlineProperty(
                outlineType = OutlineType.Rect,
                cropOutline = RectCropShape(0, "Rect")
            ),
            handleSize = 20.dp,
            aspectRatio = AspectRatio(1f),
            overlayRatio = 1f
        ),
        crop = crop,
        onCropStart = onCropStart,
        onCropSuccess = { onCropSuccess(it) },
        modifier = modifier
    )
}