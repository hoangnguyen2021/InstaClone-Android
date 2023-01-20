package myapp.hoang.media.services

import android.graphics.Bitmap
import java.io.File

interface ImageUploadService {
    suspend fun uploadProfilePic(imageFile: File): String
    suspend fun getProfilePic(profilePicPath: String): ByteArray
    suspend fun uploadPostImage(bitmap: Bitmap): String
}