package myapp.hoang.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException


object BitmapUtils {
    private const val WRITE_AND_TRUNCATE = "wt"

    fun writeBitmapToByteArray(
        bitmap: Bitmap,
        compressFormat: Bitmap.CompressFormat,
        compressQuality: Int
    ): ByteArray {
        return ByteArrayOutputStream().use {
            bitmap.compress(compressFormat, compressQuality, it)
            it.toByteArray()
        }
    }

    @Throws(FileNotFoundException::class)
    fun writeBitmapToUri(
        context: Context,
        bitmap: Bitmap,
        compressFormat: Bitmap.CompressFormat,
        compressQuality: Int,
        customOutputUri: Uri?,
    ): Uri {
        val newUri = customOutputUri ?: buildUri(context, compressFormat)

        return context.contentResolver.openOutputStream(newUri, WRITE_AND_TRUNCATE).use {
            bitmap.compress(compressFormat, compressQuality, it)
            newUri
        }
    }

    private fun buildUri(
        context: Context,
        compressFormat: Bitmap.CompressFormat,
    ): Uri =
        try {
            val ext = when (compressFormat) {
                Bitmap.CompressFormat.JPEG -> ".jpg"
                Bitmap.CompressFormat.PNG -> ".png"
                else -> ".webp"
            }
            // We have this because of a HUAWEI path bug when we use getUriForFile
            if (Build.VERSION.SDK_INT >= 29) {
                try {
                    val file = File.createTempFile(
                        "cropped",
                        ext,
                        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    )
                    getUriForFile(context, file)
                } catch (e: Exception) {
                    Log.e("AIC", "${e.message}")
                    val file = File.createTempFile("cropped", ext, context.cacheDir)
                    getUriForFile(context, file)
                }
            } else {
                Uri.fromFile(File.createTempFile("cropped", ext, context.cacheDir))
            }
        } catch (e: IOException) {
            throw RuntimeException("Failed to create temp file for output image", e)
        }
}