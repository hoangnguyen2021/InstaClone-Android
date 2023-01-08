package myapp.hoang.media.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.cropper.ImageCropper
import com.smarttoolfactory.cropper.model.AspectRatio
import com.smarttoolfactory.cropper.model.OutlineType
import com.smarttoolfactory.cropper.model.RectCropShape
import com.smarttoolfactory.cropper.settings.CropDefaults
import com.smarttoolfactory.cropper.settings.CropOutlineProperty

@Composable
fun MyImageCropper(
    imageBitmap: ImageBitmap,
    modifier: Modifier = Modifier
) {
    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    var crop by remember { mutableStateOf(false) }
    var isCropping by remember { mutableStateOf(false) }

    ImageCropper(
        modifier = modifier,
        imageBitmap = imageBitmap,
        contentDescription = "Image Cropper",
        cropStyle = CropDefaults.style(),
        cropProperties = CropDefaults.properties(
            cropOutlineProperty = CropOutlineProperty(
                OutlineType.Rect,
                RectCropShape(0, "Rect")
            ),
            handleSize = 20.dp,
            aspectRatio = AspectRatio(1f)
        ),
        crop = crop,
        onCropStart = {
            isCropping = true
        },
        onCropSuccess = {
            croppedImage = it
            isCropping = false
            crop = false
        }
    )
}