package com.typ.islamic_toolkit_kmp

import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp
import com.typ.islamic_toolkit_kmp.core.locale.LocaleManager
import com.typ.islamic_toolkit_kmp.core.location.PopularLocations
import com.typ.islamic_toolkit_kmp.hijri.lib.HijriCalendar
import com.typ.islamic_toolkit_kmp.praytimes.enums.CalculationMethod
import com.typ.islamic_toolkit_kmp.praytimes.enums.PrayType
import com.typ.islamic_toolkit_kmp.praytimes.lib.PrayerTimesCalculator
import com.typ.islamic_toolkit_kmp.praytimes.models.PrayerTimes
import com.typ.islamic_toolkit_kmp.praytimes.utils.prayerTimesCalcConfig
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class LocaleTest {

    @Test
    fun testEnglishLocale() {
        val english = LocaleManager.Locales.ENGLISH
        val curr = LocaleManager.getDefault()

        assertTrue(message = "Test failed finding: '${curr}' != '${english}'") {
            curr == english
        }
    }
}

class PrayerTimesTest {

    private lateinit var prayerTimes: PrayerTimes

    @BeforeTest
    fun prepareTest() {
        Timestamp.now.apply(::println)
        val location = PopularLocations.Egypt.CAIRO.apply(::println)

        val config: PrayerTimesCalculator.Config = prayerTimesCalcConfig {
            calcMethod = CalculationMethod.EGYPT
            useDefaultTimezone = true
        }.apply(::println)

        prayerTimes = PrayerTimes.getTodayPrays(location, config).apply(::println)
    }

    @Test
    fun testCurrentPray() {
        assertTrue { ::prayerTimes.isInitialized }
        val current = PrayerTimes.getCurrentPray(prayerTimes)

        assertTrue {
            println("Current pray is: $current")
            current.type == PrayType.FAJR
        }
    }

}

class HijriCalendarTest {

    @Test
    fun testToday() {
        listOf(
            HijriCalendar.yesterday,
            HijriCalendar.today,
            HijriCalendar.tomorrow
        ).onEach(::println)
    }

}