package myapp.hoang.media.services

import android.graphics.Bitmap
import android.net.Uri
import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video

interface MediaStoreService {
    fun getAllImages(): List<Image>
    fun getAllVideos(): List<Video>
    fun getAllMedia(): List<Media>
    fun getBitmapFromUri(uri: Uri): Bitmap
}