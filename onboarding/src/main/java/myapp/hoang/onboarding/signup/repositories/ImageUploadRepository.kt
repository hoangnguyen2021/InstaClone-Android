package myapp.hoang.onboarding.signup.repositories

import java.io.File


interface ImageUploadRepository {
    suspend fun uploadProfilePic(imageFile: File): String
    suspend fun getProfilePic(profilePicPath: String): ByteArray
}