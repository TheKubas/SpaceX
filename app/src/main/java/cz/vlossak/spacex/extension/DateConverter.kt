package cz.vlossak.spacex.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun convertDate(date: String) : LocalDateTime {
    val dateString = date
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val dateTime: LocalDateTime = LocalDateTime.parse(dateString, formatter)
    return dateTime
}

fun formatLocalDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy - HH:mm", Locale.ENGLISH)
    return dateTime.format(formatter)
}