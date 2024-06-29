@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.lvfd.islamic_toolkit_kmp.shared

import com.lvfd.islamic_toolkit_kmp.core.datetime.Timestamp
import kotlinx.datetime.Clock
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter


actual class SimpleDateFormat actual constructor(pattern: String, locale: Locale) {
    private val formatter: NSDateFormatter = NSDateFormatter().apply { dateFormat = pattern }

    actual fun format(timestamp: Timestamp): String {
        val datetime = Clock.System.now()
        return formatter.stringFromDate(datetime.toNSDate())
    }

}