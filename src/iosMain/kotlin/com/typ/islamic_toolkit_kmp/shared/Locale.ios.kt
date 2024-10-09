@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.typ.islamic_toolkit_kmp.shared

import platform.Foundation.NSLocale

actual class Locale actual constructor(language: String, country: String) {

    actual val language: String = language
    actual val country: String = country

    fun toNSLocale(): NSLocale {
        return NSLocale(
            if (country.isNotEmpty()) "${language}_${country}"
            else language
        )
    }

    actual override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false

        if (this::class != other::class) return false
        return language == (other as Locale).language
    }

    actual override fun hashCode(): Int {
        var result = language.hashCode()
        result = 31 * result + country.hashCode()
        return result
    }

    actual override fun toString(): String {
        return "Locale(language='$language', country='$country')"
    }
}