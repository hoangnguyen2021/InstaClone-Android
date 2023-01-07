package myapp.hoang.media.repositories

import myapp.hoang.media.datasources.MediaStoreDataSource
import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video

class MediaStoreRepositoryImpl(
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
}