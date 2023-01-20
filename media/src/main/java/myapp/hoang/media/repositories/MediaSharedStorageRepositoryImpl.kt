package myapp.hoang.media.repositories

import android.graphics.Bitmap
import android.net.Uri
import myapp.hoang.media.datasources.MediaSharedStorageDataSource
import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video
import javax.inject.Inject

class MediaSharedStorageRepositoryImpl @Inject constructor(
    private val mediaSharedStorageDataSource: MediaSharedStorageDataSource
): MediaSharedStorageRepository {
    override suspend fun getAllImages(): List<Image> {
        return mediaSharedStorageDataSource.getAllImages()
    }

    override suspend fun getAllVideos(): List<Video> {
        return mediaSharedStorageDataSource.getAllVideos()
    }

    override suspend fun getAllMedia(): List<Media> {
        return mediaSharedStorageDataSource.getAllMedia()
    }

    override suspend fun getBitmapFromUri(uri: Uri): Bitmap {
        return mediaSharedStorageDataSource.getBitmapFromUri(uri)
    }
}