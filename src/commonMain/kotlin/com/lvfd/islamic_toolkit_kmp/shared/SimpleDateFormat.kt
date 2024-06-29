package com.lvfd.islamic_toolkit_kmp.shared

import com.lvfd.islamic_toolkit_kmp.core.datetime.Timestamp

expect class SimpleDateFormat( pattern: String, locale: Locale) {

    fun format(timestamp: Timestamp): String

}