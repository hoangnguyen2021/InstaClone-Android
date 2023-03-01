package myapp.hoang.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.temporal.ChronoUnit

fun Instant.toRelativeTimeString(): String {
    val then = this.toJavaInstant()
    val now = Clock.System.now().toJavaInstant()

    val thenLocalDate = then.atZone(ZoneId.of("UTC")).toLocalDate()
    val nowLocalDate = now.atZone(ZoneId.systemDefault()).toLocalDate()

    val weeks = ChronoUnit.WEEKS.between(thenLocalDate, nowLocalDate)
    val days = ChronoUnit.DAYS.between(thenLocalDate, nowLocalDate)
    val hours = ChronoUnit.HOURS.between(then, now)
    val minutes = ChronoUnit.MINUTES.between(then, now)
    val seconds = ChronoUnit.SECONDS.between(then, now)
    if (weeks > 0) {
        return "${weeks}w"
    } else if (days > 0) {
        return "${days}d"
    } else if (hours > 0) {
        return "${hours}h"
    } else if (minutes > 0) {
        return "${minutes}m"
    } else if (seconds > 0) {
        return "${seconds}s"
    }
    return ""
}