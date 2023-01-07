package myapp.hoang.media.models

import android.net.Uri

open class Media(
    open val id: Long,
    open val contentUri: Uri,
    open val displayName: String,
    open val size: Long,
    open val path: String,
    open val mimeType: String,
    open val dateAdded: Long
)