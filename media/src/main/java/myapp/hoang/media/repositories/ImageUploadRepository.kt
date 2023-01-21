package myapp.hoang.media.repositories

import android.graphics.Bitmap
import java.io.File


interface ImageUploadRepository {
    suspend fun uploadProfilePic(imageFile: File): String
    suspend fun getProfilePic(profilePicPath: String): ByteArray
    suspend fun uploadPostImages(bitmaps: List<Bitmap>): String
}