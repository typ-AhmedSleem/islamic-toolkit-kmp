/*
 * This app is developed by AHMED SLEEM
 *
 * Copyright (c) 2021.  TYP INC. All Rights Reserved
 */
package com.lvfd.islamic_toolkit_kmp.hijri.models

import com.lvfd.islamic_toolkit_kmp.core.datetime.Timestamp
import com.lvfd.islamic_toolkit_kmp.core.locale.LocaleManager
import com.lvfd.islamic_toolkit_kmp.hijri.lib.HijriCalendar
import com.lvfd.islamic_toolkit_kmp.hijri.lib.ummelqura.HijriCalendarMonthType
import com.lvfd.islamic_toolkit_kmp.hijri.lib.ummelqura.HijriMonth
import com.lvfd.islamic_toolkit_kmp.hijri.lib.ummelqura.HijriMonthNameFormat
import com.lvfd.islamic_toolkit_kmp.hijri.lib.ummelqura.text.HijriCalendarMonthsNames
import com.lvfd.islamic_toolkit_kmp.shared.Locale

/**
 * Model class representing HijriDate
 */
open class HijriDate(
    val year: Int,
    /**
     * Zero-based Hijri Month index. 0 to 11
     */
    val monthNumber: Int,
    /**
     * Hijri Day of this month
     */
    val day: Int
) {

    val month: HijriMonth = HijriCalendarMonthsNames.getWithNames(HijriCalendarMonthType.entries[monthNumber])

    val shortMonthName: String
        get() = month.shortName

    val longMonthName: String
        get() = month.longName

    fun getMonthName(
        month: HijriCalendarMonthType,
        locale: Locale = LocaleManager.getDefault(),
        format: HijriMonthNameFormat = HijriMonthNameFormat.LONG
    ): String {
        return HijriCalendarMonthsNames.getWithNames(month, locale).let {
            if (format == HijriMonthNameFormat.LONG) it.longName
            else it.shortName
        }
    }

    fun toGregorian(): Timestamp = HijriCalendar.toGregorian(this)

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (this === other) return true
        if (other !is HijriDate) return false
        return day == other.day && monthNumber == other.monthNumber && year == other.year
    }

    fun isAfter(another: HijriDate?): Boolean {
        return if (another == null) false else day + monthNumber + year - (another.day + another.monthNumber + another.year) > 0
    }

    fun isBefore(another: HijriDate?): Boolean {
        return if (another == null) false else day + monthNumber + year - (another.day + another.monthNumber + another.year) < 0
    }

    override fun hashCode(): Int {
        return year.hashCode() + monthNumber.hashCode() + day.hashCode()
    }

    override fun toString(): String {
        return "HijriDate(year=$year, month=$monthNumber-${longMonthName}, day=$day)"
    }
}