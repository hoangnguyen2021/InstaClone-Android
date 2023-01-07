package myapp.hoang.media.datasources

import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video

interface MediaStoreDataSource {
    suspend fun getAllImages(): List<Image>
    suspend fun getAllVideos(): List<Video>
    suspend fun getAllMedia(): List<Media>
}