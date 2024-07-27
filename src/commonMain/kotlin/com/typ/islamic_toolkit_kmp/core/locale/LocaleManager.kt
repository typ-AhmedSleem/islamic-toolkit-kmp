package com.typ.islamic_toolkit_kmp.core.locale

import com.typ.islamic_toolkit_kmp.shared.Locale

/**
 * Locale Manager
 *
 * The `LocaleManager` object provides utility functions and management for handling locales in your application.
 * The object can be used to access locale-related functionality throughout the application.
 */
object LocaleManager {

    /**
     * This function creates a new custom locale with given language code.
     *
     * @param language Construct a locale from a language code.
     * This constructor of Locale(code) normalizes the language value to lowercase.
     *
     * @return a new Locale with the given language code.
     */
    fun custom(language: String) = Locale(language)

    /**
     * Locales
     *
     * The `Locales` object provides an easy way to get locales that are not in `java.util.Locale`
     * such as: Arabic
     */
    object Locales {
        /**
         * Locale object for Arabic
         */
        val ARABIC = custom("ar")
        val ENGLISH = custom("en")
    }

}