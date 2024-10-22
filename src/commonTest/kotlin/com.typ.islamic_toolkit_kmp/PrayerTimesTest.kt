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
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PrayerTimesTest {

    private lateinit var prayerTimes: PrayerTimes

    @Test
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

        val tomorrow = today
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
        println(
            """
            
            |   Pray   |   Old method   |  New method    |
            |----------|----------------|----------------|
            |  Fajr    |    ${o.fajr.formattedTime}    |    ${n.fajr.formattedTime}    |
            |  Sunrise |    ${o.sunrise.formattedTime}    |    ${n.sunrise.formattedTime}    |
            |  Dhuhr   |    ${o.dhuhr.formattedTime}    |    ${n.dhuhr.formattedTime}    |
            |  Asr     |    ${o.asr.formattedTime}    |    ${n.asr.formattedTime}    |
            |  Maghrib |    ${o.maghrib.formattedTime}    |    ${n.maghrib.formattedTime}    |
            |  Isha    |    ${o.isha.formattedTime}    |    ${n.isha.formattedTime}    |
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