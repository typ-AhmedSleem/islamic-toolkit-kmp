@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.typ.shared

import java.util.Locale as JvmLocale


actual class Locale actual constructor(language: String, country: String) {

    private val _jvmLocale = JvmLocale(language, country)

    actual val language: String = language
    actual val country: String = country

    val jvmLocale: JvmLocale
        get() = _jvmLocale

    actual override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

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