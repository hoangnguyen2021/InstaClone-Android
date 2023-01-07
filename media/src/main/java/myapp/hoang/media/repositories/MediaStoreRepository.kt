package myapp.hoang.media.repositories

import myapp.hoang.media.models.Image
import myapp.hoang.media.models.Media
import myapp.hoang.media.models.Video

interface MediaStoreRepository {
    suspend fun getAllImages(): List<Image>
    suspend fun getAllVideos(): List<Video>
    suspend fun getAllMedia(): List<Media>
}