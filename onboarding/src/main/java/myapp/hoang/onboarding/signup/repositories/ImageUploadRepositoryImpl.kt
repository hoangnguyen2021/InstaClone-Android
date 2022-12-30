package myapp.hoang.onboarding.signup.repositories

import myapp.hoang.onboarding.signup.services.ImageUploadService
import java.io.File
import javax.inject.Inject

class ImageUploadRepositoryImpl @Inject constructor(
    private val imageUploadService: ImageUploadService
): ImageUploadRepository {
    override suspend fun uploadProfilePic(imageFile: File): String {
        return imageUploadService.uploadProfilePic(imageFile)
    }

    override suspend fun getProfilePic(profilePicPath: String): ByteArray {
        return imageUploadService.getProfilePic(profilePicPath)
    }
}