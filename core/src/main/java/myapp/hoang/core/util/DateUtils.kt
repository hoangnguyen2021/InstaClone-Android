package myapp.hoang.core.util

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object DateUtils {
    val years = (1900..2022).map { it.toString() }
    val monthsNumber = (1..12).map { it.toString() }
    val days = (1..31).map { it.toString() }
    val monthsNames = listOf(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    )

    val currentMonth = LocalDate.now().monthValue
    val currentDay = LocalDate.now().dayOfMonth
    val currentYear = LocalDate.now().year

    fun getToday(): LocalDate = LocalDate.now()

    fun getTodayString(): String {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(LocalDate.now())
    }

    fun getDateString(date: LocalDate): String {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date)
    }

    fun parseDateString(dateString: String): LocalDate {
        if (dateString.isEmpty()) return LocalDate.now()

        return LocalDate.parse(dateString, DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
    }

    fun calculateAge(birthDate: LocalDate): Int {
        return Period.between(birthDate, LocalDate.now()).years
    }
}