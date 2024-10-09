@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.typ.islamic_toolkit_kmp.shared

import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter


actual class SimpleDateFormat actual constructor(pattern: String, locale: Locale) {
    private val formatter: NSDateFormatter = NSDateFormatter().apply {
        this.dateFormat = pattern
        this.locale = locale.toNSLocale()
    }

    actual fun format(timestamp: Timestamp): String {
        return formatter.stringFromDate(timestamp.instant.toNSDate())
    }

}