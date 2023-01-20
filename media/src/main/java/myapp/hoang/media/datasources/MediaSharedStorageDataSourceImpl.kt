package myapp.hoang.media.datasources

import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.withContext
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video
import myapp.hoang.media.services.MediaSharedStorageService
import javax.inject.Inject

class MediaSharedStorageDataSourceImpl @Inject constructor(
    private val mediaSharedStorageService: MediaSharedStorageService,
    private val dispatcherProvider: DispatcherProvider
): MediaSharedStorageDataSource {
    override suspend fun getAllImages(): List<Image> {
        return withContext(dispatcherProvider.io) {
            mediaSharedStorageService.getAllImages()
        }
    }

    override suspend fun getAllVideos(): List<Video> {
        return withContext(dispatcherProvider.io) {
            mediaSharedStorageService.getAllVideos()
        }
    }

    override suspend fun getAllMedia(): List<Media> {
        return withContext(dispatcherProvider.io) {
            mediaSharedStorageService.getAllMedia()
        }
    }

    override suspend fun getBitmapFromUri(uri: Uri): Bitmap {
        return withContext(dispatcherProvider.io) {
            mediaSharedStorageService.getBitmapFromUri(uri)
        }
    }
}