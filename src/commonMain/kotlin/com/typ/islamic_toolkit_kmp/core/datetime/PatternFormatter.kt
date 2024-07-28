/*
 * This app is developed by AHMED SLEEM
 *
 * Copyright (c) 2021.  TYP INC. All Rights Reserved
 */
package com.typ.islamic_toolkit_kmp.core.datetime

import com.typ.islamic_toolkit_kmp.core.datetime.PatternFormatter.Companion.custom
import com.typ.islamic_toolkit_kmp.core.datetime.PatternFormatter.PrayTimes
import com.typ.islamic_toolkit_kmp.core.locale.LocaleManager
import com.typ.islamic_toolkit_kmp.core.locale.LocaleManager.Locales
import com.typ.islamic_toolkit_kmp.shared.Locale
import com.typ.islamic_toolkit_kmp.shared.SimpleDateFormat

/**
 * Pattern Formatter for datetime objects
 *
 * This class represents a format pattern that can be used for formatting datetime.
 * The `FormatPattern` class has a private constructor, and instances of this class can be
 * created using the companion object's custom function or pre-created formats.
 *
 * @property pattern The string pattern used for formatting prayer times.
 *
 * @constructor Private constructor to create an instance of `FormatPattern`.
 *
 * @see PrayTimes for default pattern used by modules/praytimes
 * @see custom for creating custom patterns.
 */
open class PatternFormatter private constructor(private val pattern: String) {
    /**
     * Pattern [hh:mm aa]. eg: 3:25 pm
     */
    class Time12SX : PatternFormatter("hh:mm aa")

    /**
     * Pattern [hh:mm]. eg: 3:25
     */
    class Time12NSX : PatternFormatter("hh:mm")

    /**
     * Pattern [HH:mm]. eg: 15:30
     */
    class Time24 : PatternFormatter("HH:mm")

    /**
     * Pattern [dd MMM]. eg: 01 Aug
     */
    class DateMonth : PatternFormatter("dd MMM")

    /**
     * Pattern [dd/MM/yyyy]. eg: 01/08/2001
     */
    class DateShort : PatternFormatter("dd/MM/yyyy")

    /**
     * Pattern [dd MMM yyyy]. eg: 01 Aug 2001
     */
    class DateNormal : PatternFormatter("dd MMM yyyy")

    /**
     * Pattern [dd MMM yyyy]. eg: 01 Aug 2001
     */
    class DateFull : PatternFormatter("dd MMMM yyyy")

    /**
     * Pattern [dd/MM/yyyy hh:mm aa]. eg: 01/08/2001 12:03 am
     */
    class DateTimeNormal : PatternFormatter("dd/MM/yyyy hh:mm aa")

    /**
     * Pattern [dd MMM yyyy hh:mm aa]. eg: 01 Aug 2001 12:03 am
     */
    class DateTimeFull : PatternFormatter("dd MMM yyyy hh:mm aa")

    /**
     * Default Format Pattern for Prayer Times.
     *
     * This constant represents the default format pattern for displaying prayer times.
     * It is initialized with a specific format pattern implementation, `TIME12SX`.
     * This format pattern uses the 12-hour clock format with 'AM' and 'PM' indicators and
     * supports displaying additional prayer times (such as Sunrise and Midnight).
     *
     * The `praytimesDefault` constant is of type `FormatPattern`, which is an interface
     * that defines the contract for formatting prayer times.
     * You can use this constant to display prayer times in the default format throughout your application.
     *
     * @see PatternFormatter.Time12SX
     */
    class PrayTimes : PatternFormatter("hh:mm aa")

    /**
     * Formats the given timestamp using a specified pattern and locale.
     *
     * This function formats the provided `timestamp` using the `pattern` and default locale returned
     * from `LocaleManager.getDefault()`
     * The resulting formatted date and time string is returned as a `String`.
     *
     * @param timestamp The timestamp to be formatted.
     * @return The formatted date and time string based on the specified `pattern` and `locale`.
     *
     * @see LocaleManager for more information about locales.
     *
     * NOTE: This uses English locale by default. see [Locales.ENGLISH]
     */
    fun format(timestamp: Timestamp) = format(timestamp, Locales.ENGLISH)


    /**
     * Formats the given timestamp using a specified pattern and locale.
     *
     * This function formats the provided `timestamp` using the `pattern` and `locale` parameters.
     * The resulting formatted date and time string is returned as a `String`.
     *
     * @param timestamp The timestamp to be formatted.
     * @param locale The locale to be used for formatting the timestamp.
     * @return The formatted date and time string based on the specified `pattern` and `locale`.
     *
     */
    fun format(timestamp: Timestamp, locale: Locale): String {
        return SimpleDateFormat(pattern, locale).format(timestamp)
    }

    companion object {

        /**
         * Create a Custom Format Pattern for Prayer Times.
         *
         * This function allows you to create a custom format pattern for displaying prayer times.
         * It takes a `pattern` parameter, which is a string representing the desired format pattern.
         * The `pattern` string should follow the conventions defined by the `FormatPattern` class.
         *
         * @param pattern The custom format pattern to be used for formatting prayer times.
         * @return A new `FormatPattern` object with the specified custom format pattern.
         *
         */
        fun custom(pattern: String) = PatternFormatter(pattern)
    }
}
