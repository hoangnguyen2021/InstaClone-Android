package myapp.hoang.core.util

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.content.FileProvider
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths

fun Context.authority() = "$packageName.provider"

fun getUriForFile(context: Context, file: File): Uri {
    val authority = context.authority()
    try {
        Log.i("AIC", "Try get URI for scope storage - content://")
        return FileProvider.getUriForFile(context, authority, file)
    } catch (e: Exception) {
        try {
            Log.e("AIC", "${e.message}")
            Log.w(
                "AIC",
                "ANR Risk -- Copying the file the location cache to avoid 'external-files-path' bug for N+ devices",
            )
            // Note: Periodically clear this cache
            val cacheFolder = File(context.cacheDir, "CROP_LIB_CACHE")
            val cacheLocation = File(cacheFolder, file.name)
            var input: InputStream? = null
            var output: OutputStream? = null
            try {
                input = FileInputStream(file)
                output = FileOutputStream(cacheLocation) // appending output stream
                input.copyTo(output)
                Log.i(
                    "AIC",
                    "Completed Android N+ file copy. Attempting to return the cached file",
                )
                return FileProvider.getUriForFile(context, authority, cacheLocation)
            } catch (e: Exception) {
                Log.e("AIC", "${e.message}")
                Log.i("AIC", "Trying to provide URI manually")
                val path = "content://$authority/files/my_images/"

                if (Build.VERSION.SDK_INT >= 26) {
                    Files.createDirectories(Paths.get(path))
                } else {
                    val directory = File(path)
                    if (!directory.exists()) directory.mkdirs()
                }

                return Uri.parse("$path${file.name}")
            } finally {
                input?.close()
                output?.close()
            }
        } catch (e: Exception) {
            Log.e("AIC", "${e.message}")

            if (Build.VERSION.SDK_INT < 29) {
                val cacheDir = context.externalCacheDir
                cacheDir?.let {
                    try {
                        Log.i(
                            "AIC",
                            "Use External storage, do not work for OS 29 and above",
                        )
                        return Uri.fromFile(File(cacheDir.path, file.absolutePath))
                    } catch (e: Exception) {
                        Log.e("AIC", "${e.message}")
                    }
                }
            }
            // If nothing else work we try
            Log.i("AIC", "Try get URI using file://")
            return Uri.fromFile(file)
        }
    }
}