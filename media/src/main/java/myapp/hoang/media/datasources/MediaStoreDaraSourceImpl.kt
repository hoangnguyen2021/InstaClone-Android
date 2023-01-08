package myapp.hoang.media.datasources

import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.withContext
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video
import myapp.hoang.media.services.MediaStoreService
import javax.inject.Inject

class MediaStoreDaraSourceImpl @Inject constructor(
    private val mediaStoreService: MediaStoreService,
    private val dispatcherProvider: DispatcherProvider
): MediaStoreDataSource {
    override suspend fun getAllImages(): List<Image> {
        return withContext(dispatcherProvider.io) {
            mediaStoreService.getAllImages()
        }
    }

    override suspend fun getAllVideos(): List<Video> {
        return withContext(dispatcherProvider.io) {
            mediaStoreService.getAllVideos()
        }
    }

    override suspend fun getAllMedia(): List<Media> {
        return withContext(dispatcherProvider.io) {
            mediaStoreService.getAllMedia()
        }
    }

    override suspend fun getBitmapFromUri(uri: Uri): Bitmap {
        return withContext(dispatcherProvider.io) {
            mediaStoreService.getBitmapFromUri(uri)
        }
    }
}