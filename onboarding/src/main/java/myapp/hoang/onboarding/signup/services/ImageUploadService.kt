package myapp.hoang.onboarding.signup.services

import java.io.File

interface ImageUploadService {
    suspend fun uploadProfilePic(imageFile: File): String
    suspend fun getProfilePic(profilePicPath: String): ByteArray
}