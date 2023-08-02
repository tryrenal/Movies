package com.redveloper.movies.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun convertDateStringToDate(inputDateString: String): Date? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MMMM/yyyy", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    return try {
        val date = inputFormat.parse(inputDateString)
        outputFormat.parse(outputFormat.format(date))
    } catch (e: Exception) {
        null
    }
}
