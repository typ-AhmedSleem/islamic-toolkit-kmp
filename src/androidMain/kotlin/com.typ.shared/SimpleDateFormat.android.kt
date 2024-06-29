@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.typ.shared

import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp
import java.util.Date
import java.text.SimpleDateFormat as JvmSimpleDateFormat

actual class SimpleDateFormat actual constructor(pattern: String, locale: Locale) {

    private val formatter = JvmSimpleDateFormat(pattern, locale.jvmLocale)

    actual fun format(timestamp: Timestamp): String {
        return formatter.format(Date(timestamp.millis))
    }

}