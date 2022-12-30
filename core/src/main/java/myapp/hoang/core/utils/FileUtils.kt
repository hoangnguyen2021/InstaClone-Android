package myapp.hoang.core.utils

import android.content.Context
import android.net.Uri
import java.io.File

object FileUtils {
    fun createTmpFileUri(context: Context): Uri {
        val tmpFile = File
            .createTempFile("tmp_image_file", ".png", context.cacheDir)
            .apply {
                createNewFile()
                deleteOnExit()
            }

        return getUriForFile(context, tmpFile)
    }

    fun getUriFilePath(context: Context, uri: Uri, uniqueName: Boolean = false): String {
        return getFilePathFromUri(context, uri, uniqueName)
    }
}