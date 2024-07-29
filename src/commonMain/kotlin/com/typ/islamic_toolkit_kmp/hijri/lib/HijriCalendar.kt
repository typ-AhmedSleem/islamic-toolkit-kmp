/*
 * This app is developed by AHMED SLEEM
 *
 * Copyright (c) 2021.  TYP INC. All Rights Reserved
 */
package com.typ.islamic_toolkit_kmp.hijri.lib

import com.typ.islamic_toolkit_kmp.core.annotations.IntRange
import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp
import com.typ.islamic_toolkit_kmp.hijri.lib.ummelqura.HijriCalendarMonthType
import com.typ.islamic_toolkit_kmp.hijri.lib.ummelqura.UmmalquraGregorianConverter
import com.typ.islamic_toolkit_kmp.hijri.lib.ummelqura.UmmalquraGregorianConverter.getDaysInMonth
import com.typ.islamic_toolkit_kmp.hijri.models.HijriDate
import com.typ.islamic_toolkit_kmp.hijri.utils.toHijri
import kotlinx.datetime.LocalDate
import kotlin.jvm.JvmStatic

/**
 * Contains necessary code that works with Hijri Dates
 */
object HijriCalendar {

    @JvmStatic
    val yesterday: HijriDate
        get() = Timestamp.yesterday.toHijri()

    @JvmStatic
    val today: HijriDate
        get() = Timestamp.now.toHijri()

    @JvmStatic
    val tomorrow: HijriDate
        get() = Timestamp.tomorrow.toHijri()

    @JvmStatic
    fun getHijriDates(dates: List<Timestamp>): List<HijriDate> = dates.map(::toHijri)

    @JvmStatic
    fun toHijri(date: Timestamp): HijriDate {
        return UmmalquraGregorianConverter.toHijri(date).run { HijriDate(this[0], this[1], this[2]) }
    }

    @JvmStatic
    fun toGregorian(hijriDate: HijriDate): Timestamp {
        return UmmalquraGregorianConverter.toGregorian(hijriDate.year, hijriDate.monthNumber, hijriDate.day).run {
            Timestamp(LocalDate(this[0], this[1] + 1, this[2]))
        }
    }

    /**
     * Returns the length of a Hijri month in a Hijri year.
     *
     * @param hYear  Hijri year
     * @param hMonth Zero-based hijri month number
     *
     * @return the length of the month in days
     */
    @JvmStatic
    fun lengthOfMonth(hYear: Int, @IntRange(from = 0, to = 11) hMonth: Int): Int {
        return getDaysInMonth(hYear, hMonth)
    }

    /**
     * Returns the length of the given year.
     *
     * This returns the length of the year in days, either
     * 354 or 355.
     *
     * @param year the year to calculate day count for.
     * @return 355 if the year is leap, 354 otherwise
     */
    fun lengthOfYear(year: Int): Int {
        var total = 0
        for (month in HijriCalendarMonthType.entries) {
            total += lengthOfMonth(year, month.ordinal + 1)
        }
        return total
    }
}