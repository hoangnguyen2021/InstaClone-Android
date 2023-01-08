package myapp.hoang.media.repositories

import android.graphics.Bitmap
import android.net.Uri
import myapp.hoang.media.datasources.MediaStoreDataSource
import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video
import javax.inject.Inject

class MediaStoreRepositoryImpl @Inject constructor(
    private val mediaStoreDataSource: MediaStoreDataSource
): MediaStoreRepository {
    override suspend fun getAllImages(): List<Image> {
        return mediaStoreDataSource.getAllImages()
    }

    override suspend fun getAllVideos(): List<Video> {
        return mediaStoreDataSource.getAllVideos()
    }

    override suspend fun getAllMedia(): List<Media> {
        return mediaStoreDataSource.getAllMedia()
    }

    override suspend fun getBitmapFromUri(uri: Uri): Bitmap {
        return mediaStoreDataSource.getBitmapFromUri(uri)
    }
}