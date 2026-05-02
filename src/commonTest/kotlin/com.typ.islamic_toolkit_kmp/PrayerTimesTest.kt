package com.typ.islamic_toolkit_kmp

import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp
import com.typ.islamic_toolkit_kmp.core.location.Location
import com.typ.islamic_toolkit_kmp.core.location.PopularLocations
import com.typ.islamic_toolkit_kmp.hijri.lib.HijriCalendar
import com.typ.islamic_toolkit_kmp.praytimes.enums.CalculationMethod
import com.typ.islamic_toolkit_kmp.praytimes.lib.PrayerTimesCalculator
import com.typ.islamic_toolkit_kmp.praytimes.models.PrayerTimes
import com.typ.islamic_toolkit_kmp.praytimes.utils.prayerTimesCalcConfig
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.typ.islamic_toolkit_kmp.core.datetime.PatternFormatter.Companion.custom
import com.typ.islamic_toolkit_kmp.core.locale.LocaleManager

class PrayerTimesTest {

    private lateinit var prayerTimes: PrayerTimes

    @BeforeTest
    fun prepareTest() {
        Timestamp.now.apply(::println)
        val location = Location(
            code = "EG",
            latitude = 30.647093,
            longitude = 31.394939,
            timezone = 2.0
        ).also(::println)

        val config: PrayerTimesCalculator.Config = prayerTimesCalcConfig {
            calcMethod = CalculationMethod.EGYPT
            useDefaultTimezone = true
        }.apply(::println)

        prayerTimes = PrayerTimes.getTodayPrays(location, config)
    }

    @Test
    fun testCurrentPray() {
        assertTrue { ::prayerTimes.isInitialized }
        val current = prayerTimes.currentPray

        assertFalse {
            println("Current pray is: $current")
            current.passed
        }
    }

    @Test
    fun testTimestamp() {
        val date = LocalDateTime(
            year = 2024,
            monthNumber = 10,
            dayOfMonth = 31,
            hour = 15,
            minute = 24,
        )
        val today = Timestamp(
            date = date.date,
            time = date.time
        ).also {
            println("Today is: $it")
        }

        today
            .nextDay
            .also {
                println("Tomorrow is: $it")
            }


        val prays = PrayerTimes.getPrays(
            timestamp = today,
            location = PopularLocations.Egypt.CAIRO,
            config = prayerTimesCalcConfig {
                calcMethod = CalculationMethod.EGYPT
                useDefaultTimezone = true
            },
        )

        println(prays)
    }

    @Test
    fun testOldVsNew() {
        // * Prepare calculator runtime
        val today = Timestamp.now.apply(::println)
        val cairo = PopularLocations.Egypt.CAIRO
        Location(
            code = "EG",
            latitude = 30.647093,
            longitude = 31.394939,
            timezone = 2.0
        ).also(::println)
        val config: PrayerTimesCalculator.Config = prayerTimesCalcConfig {
            calcMethod = CalculationMethod.EGYPT
            useDefaultTimezone = true
        }.apply(::println)

        // * Calculate prayer times using old calculator
        val n = PrayerTimes.getPrays(
            location = cairo,
            timestamp = today,
            config = config
        )
        // * Calculate prayer times using new calculator
        val o = PrayerTimes.getPrays(
            location = cairo,
            timestamp = today,
            config = config
        )

        // * Print results and compare them
        val formatter = custom("hh:mm:ss aa")
        println(
            """
            
            |   Pray   |   Old method   |  New method    |
            |----------|----------------|----------------|
            |  Fajr    |  ${o.fajr.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |  ${n.fajr.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |
            |  Sunrise |  ${o.sunrise.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |  ${n.sunrise.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |
            |  Dhuhr   |  ${o.dhuhr.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |  ${n.dhuhr.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |
            |  Asr     |  ${o.asr.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |  ${n.asr.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |
            |  Maghrib |  ${o.maghrib.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |  ${n.maghrib.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |
            |  Isha    |  ${o.isha.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |  ${n.isha.getFormattedTime(formatter, LocaleManager.Locales.ENGLISH)}  |
            ----------------------------------------------
            """.trimIndent()
        )
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