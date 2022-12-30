package myapp.hoang.core.utils

import java.time.Instant

object TimeUtils {
    fun getCurrentEpochMilli(): Long = Instant.now().toEpochMilli()
}