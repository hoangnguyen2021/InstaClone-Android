package myapp.hoang.core.utils

import java.time.*
import java.time.temporal.ChronoUnit

object TimeUtils {
    fun getCurrentEpochMilli(): Long = Instant.now().toEpochMilli()
}