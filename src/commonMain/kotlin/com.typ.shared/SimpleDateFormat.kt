@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.typ.shared

import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp

expect class SimpleDateFormat( pattern: String, locale: Locale) {

    fun format(timestamp: Timestamp): String

}