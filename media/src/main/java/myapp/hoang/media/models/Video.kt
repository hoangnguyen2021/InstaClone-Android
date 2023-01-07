package myapp.hoang.media.models

import android.net.Uri

data class Video(
    override val id: Long,
    override val contentUri: Uri,
    override val displayName: String,
    override val size: Long,
    override val path: String,
    override val mimeType: String,
    override val dateAdded: Long,
    val duration: Long
): Media(id, contentUri, displayName, size, path, mimeType, dateAdded)