package myapp.hoang.media.repositories

import android.graphics.Bitmap
import myapp.hoang.media.models.ImageFilter

interface ImageFilterRepository {
    suspend fun getAllImageFilters(unfilteredBitmap: Bitmap): List<ImageFilter>
}