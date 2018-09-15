package com.raju.socialplatform.utilities

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    val FORMAT_1 = "yyyy-MM-dd HH:mm:ss"
    val FORMAT_3 = "dd MMM yyyy"
    val FORMAT_2 = "yyyy-MM-dd HH:mm"

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat(FORMAT_3, Locale.US)
        calendar.time = Date()
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(calendar.time)
    }

    fun getCurrentDateTime(): String {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat(FORMAT_1, Locale.US)
        calendar.time = Date()
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(calendar.time)
    }
}