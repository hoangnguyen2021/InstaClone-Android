package myapp.hoang.media.repositories

import android.graphics.Bitmap
import kotlinx.coroutines.withContext
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.media.services.ImageUploadService
import java.io.File
import javax.inject.Inject

class ImageUploadRepositoryImpl @Inject constructor(
    private val imageUploadService: ImageUploadService,
    private val dispatcherProvider: DispatcherProvider
): ImageUploadRepository {
    override suspend fun uploadProfilePic(imageFile: File): String {
        return  withContext(dispatcherProvider.io) {
            imageUploadService.uploadProfilePic(imageFile)
        }
    }

    override suspend fun getProfilePic(profilePicPath: String): ByteArray {
        return withContext(dispatcherProvider.io) {
            imageUploadService.getProfilePic(profilePicPath)
        }
    }

    override suspend fun uploadPostImages(bitmaps: List<Bitmap>): String {
        return withContext(dispatcherProvider.io) {
            imageUploadService.uploadPostImages(bitmaps)
        }
    }
}