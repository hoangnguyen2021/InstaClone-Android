package myapp.hoang.media.components

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.cropper.ImageCropper
import com.smarttoolfactory.cropper.model.AspectRatio
import com.smarttoolfactory.cropper.model.OutlineType
import com.smarttoolfactory.cropper.model.RectCropShape
import com.smarttoolfactory.cropper.settings.CropDefaults
import com.smarttoolfactory.cropper.settings.CropOutlineProperty

@Composable
fun InstaCloneCropper(
    imageBitmap: ImageBitmap,
    crop: Boolean,
    onCropChange: (Boolean) -> Unit,
    onCroppedImageBitmapChange: (ImageBitmap) -> Unit,
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
            aspectRatio = AspectRatio(1f)
        ),
        crop = crop,
        onCropStart = {
            Log.d("MYTAG", "Hi")
        },
        onCropSuccess = {
            onCropChange(false)
            onCroppedImageBitmapChange(it)
        },
        modifier = modifier
    )
}