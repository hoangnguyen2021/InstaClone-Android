package myapp.hoang.media.models

import android.net.Uri

data class Video(
    override val id: Long,
    override val contentUri: Uri,
    override val displayName: String,
    override val title: String,
    override val size: Long,
    override val path: String,
    override val mimeType: String,
    val duration: Long,
    override val dateAdded: Int
): Media(id, contentUri, displayName, title, size, path, mimeType, dateAdded)