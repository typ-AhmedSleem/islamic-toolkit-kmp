package com.typ.islamic_toolkit_kmp

import com.raedghazal.kotlinx_datetime_ext.atEndOfDay
import com.typ.islamic_toolkit_kmp.core.datetime.CalendarField
import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp
import com.typ.islamic_toolkit_kmp.core.location.PopularLocations
import com.typ.islamic_toolkit_kmp.hijri.lib.HijriCalendar
import com.typ.islamic_toolkit_kmp.praytimes.enums.CalculationMethod
import com.typ.islamic_toolkit_kmp.praytimes.enums.PrayType
import com.typ.islamic_toolkit_kmp.praytimes.lib.PrayerTimesCalculator
import com.typ.islamic_toolkit_kmp.praytimes.models.PrayerTimes
import com.typ.islamic_toolkit_kmp.praytimes.utils.prayerTimesCalcConfig
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PrayerTimesTest {

    private lateinit var prayerTimes: PrayerTimes

    @Test
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
            date= date.date,
            time= date.time
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