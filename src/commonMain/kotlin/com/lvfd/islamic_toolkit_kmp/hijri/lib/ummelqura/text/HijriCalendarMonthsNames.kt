package com.lvfd.islamic_toolkit_kmp.hijri.lib.ummelqura.text

import com.lvfd.islamic_toolkit_kmp.core.locale.LocaleManager
import com.lvfd.islamic_toolkit_kmp.hijri.lib.ummelqura.HijriCalendarMonthType
import com.lvfd.islamic_toolkit_kmp.hijri.lib.ummelqura.HijriMonth
import com.lvfd.islamic_toolkit_kmp.shared.Locale

abstract class HijriCalendarMonthsNames {

    abstract val longNames: Map<Int, String>
    abstract val shortNames: Map<Int, String>

    companion object {
        fun getWithNames(type: HijriCalendarMonthType, locale: Locale = LocaleManager.getDefault()): HijriMonth {
            val names = when (locale) {
                LocaleManager.Locales.ARABIC -> {
                    val short = HijriCalendarMonthsArabic.shortNames[type.ordinal + 1]!!
                    val long = HijriCalendarMonthsArabic.longNames[type.ordinal + 1]!!

                    short to long
                }

                else -> {
                    val short = HijriCalendarMonthsEnglish.shortNames[type.ordinal + 1]!!
                    val long = HijriCalendarMonthsEnglish.longNames[type.ordinal + 1]!!

                    short to long
                }
            }

            return HijriMonth(
                type = type,
                shortName = names.first,
                longName = names.second,
            )
        }
    }

}