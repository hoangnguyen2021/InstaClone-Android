package myapp.hoang.media.repositories

import java.io.File


interface ImageUploadRepository {
    suspend fun uploadProfilePic(imageFile: File): String
    suspend fun getProfilePic(profilePicPath: String): ByteArray
}