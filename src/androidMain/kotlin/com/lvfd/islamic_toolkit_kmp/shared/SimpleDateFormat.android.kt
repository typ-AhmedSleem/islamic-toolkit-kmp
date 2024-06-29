package com.lvfd.islamic_toolkit_kmp.shared

import com.lvfd.islamic_toolkit_kmp.core.datetime.Timestamp
import java.text.SimpleDateFormat
import java.util.Date

actual class SimpleDateFormat actual constructor(pattern: String, locale: Locale) {

    private val formatter = SimpleDateFormat(pattern, locale.jvmLocale)

    actual fun format(timestamp: Timestamp): String {
        return formatter.format(Date(timestamp.millis))
    }

}