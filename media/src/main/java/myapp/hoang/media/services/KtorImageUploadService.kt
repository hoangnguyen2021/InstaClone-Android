package myapp.hoang.media.services

import android.graphics.Bitmap
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import myapp.hoang.core.config.NetworkConfig
import myapp.hoang.core.utils.BitmapUtils
import myapp.hoang.core.utils.TimeUtils
import java.io.File
import javax.inject.Inject

class KtorImageUploadService @Inject constructor(
    private val client: HttpClient
): ImageUploadService {
    override suspend fun uploadProfilePic(imageFile: File): String {
        val timestamp = TimeUtils.getCurrentEpochMilli()

        return client.post {
            url(NetworkConfig.ROUTE_UPLOAD_PROFILE_PIC)
            setBody(MultiPartFormDataContent(
                formData {
                    append("description", "user profile pic")
                    append(
                        "profile-pic",
                        imageFile.readBytes(),
                        Headers.build {
                            append(HttpHeaders.ContentType, "image/${imageFile.extension}")
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=\"user-profile-pic-$timestamp.jpg\""
                            )
                        }
                    )
                }
            ))
            onUpload { bytesSentTotal, contentLength ->
                println("Sent $bytesSentTotal bytes from $contentLength")
            }
        }.body()
    }

    override suspend fun getProfilePic(profilePicPath: String): ByteArray {
        return client.get {
            url(NetworkConfig.ROUTE_GET_PROFILE_PIC)
            parameter("objectKey", profilePicPath)
        }.body()
    }

    override suspend fun uploadPostImages(bitmaps: List<Bitmap>): List<String> {
        val timestamp = TimeUtils.getCurrentEpochMilli()
        val description = "post-images-$timestamp"

        return client.post {
            url(NetworkConfig.ROUTE_UPLOAD_IMAGES)
            setBody(MultiPartFormDataContent(
                formData {
                    append("description", description)
                    bitmaps.forEachIndexed { index, bitmap ->
                        val fileName = "$description-$index.jpeg"
                        append(
                            fileName,
                            BitmapUtils.writeBitmapToByteArray(
                                bitmap, Bitmap.CompressFormat.JPEG, 90
                            ),
                            Headers.build {
                                append(HttpHeaders.ContentType, "image/jpeg")
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "filename=\"$fileName\""
                                )
                            }
                        )
                    }
                }
            ))
            onUpload { bytesSentTotal, contentLength ->
                Log.d(TAG, "Sent $bytesSentTotal bytes from $contentLength")
            }
        }.body()
    }

    companion object {
        private val TAG = KtorImageUploadService::class.java.simpleName
    }
}