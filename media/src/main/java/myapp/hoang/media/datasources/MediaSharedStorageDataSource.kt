package myapp.hoang.media.datasources

import android.graphics.Bitmap
import android.net.Uri
import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video

interface MediaSharedStorageDataSource {
    suspend fun getAllImages(): List<Image>
    suspend fun getAllVideos(): List<Video>
    suspend fun getAllMedia(): List<Media>
    suspend fun getBitmapFromUri(uri: Uri): Bitmap
}