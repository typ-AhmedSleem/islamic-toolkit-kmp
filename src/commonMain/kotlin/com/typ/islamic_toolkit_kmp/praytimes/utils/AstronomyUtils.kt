package com.typ.islamic_toolkit_kmp.praytimes.utils

import com.typ.islamic_toolkit_kmp.core.annotations.NeedsTesting
import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp
import com.typ.islamic_toolkit_kmp.praytimes.enums.HigherLatitudeMethod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.number
import kotlin.math.floor

object AstronomyUtils {

    /**
     * Range reduce hours to 0..23
     */
    fun fixHour(a: Double): Double {
        var temp = a
        temp -= 24.0 * floor(temp / 24.0)
        temp = if (temp < 0) temp + 24 else temp
        return temp
    }

    /** Calculate julian date from a calendar date */
    @NeedsTesting("dayOfWeek needs to be tested.")
    fun calculateJulianDate(date: LocalDate): Double {
        var tYear = date.year
        var tMonth = date.month.number
        if (tMonth <= 2) {
            tYear -= 1
            tMonth += 12
        }

        val A = floor(tYear / 100.0)
        val B = 2 - A + floor(A / 4.0)

        return floor(365.25 * (tYear + 4716)) + floor(30.6001 * (tMonth + 1)) + date.dayOfMonth + B - 1524.5
    }

    /** Convert a calendar date to julian date (second method) */
    fun calcJD(year: Int, month: Int, day: Int): Double {
        val j1970 = 2440588.0
        val timestamp = Timestamp(LocalDate(year, month - 1, day))
        val days = floor(timestamp.millis.toDouble() / (1000.0 * 60.0 * 60.0 * 24.0))

        return j1970 + days - 0.5
    }

    /** References:
     * http://www.ummah.net/astronomy/saltime
     * http://aa.usno.navy.mil/faq/docs/SunApprox.html
     * Compute declination angle of sun and equation of time */
    private fun calculateSunPosition(jd: Double): DoubleArray {
        val D = jd - 2451545
        val g = PrayerTimesMath.finAngle(357.529 + 0.98560028 * D)
        val q = PrayerTimesMath.finAngle(280.459 + 0.98564736 * D)
        val L = PrayerTimesMath.finAngle(q + 1.915 * PrayerTimesMath.dSin(g) + 0.020 * PrayerTimesMath.dSin(2 * g))

        // double R = 1.00014 - 0.01671 * [self dcos:g] - 0.00014 * [self dcos:(2*g)];
        val e = 23.439 - 0.00000036 * D
        val d = PrayerTimesMath.dArcSin(PrayerTimesMath.dSin(e) * PrayerTimesMath.dSin(L))
        var RA = PrayerTimesMath.dArcTan2(PrayerTimesMath.dCos(e) * PrayerTimesMath.dSin(L), PrayerTimesMath.dCos(L)) / 15.0
        RA = fixHour(RA)

        val equOfTime = q / 15.0 - RA

        val sPosition = DoubleArray(2)
        sPosition[0] = d
        sPosition[1] = equOfTime

        return sPosition
    }

    /** Compute declination angle of sun */
    fun calculateSunDeclination(jd: Double): Double = calculateSunPosition(jd)[0]

    /** Compute equation of time */
    private fun calculateEquOfTime(jd: Double): Double = calculateSunPosition(jd)[1]

    /** Compute mid-day (Dhuhr, Zawal) time */
    fun calculateMidDay(jDate: Double, t: Double): Double {
        val eot = calculateEquOfTime(jDate + t)
        return fixHour(12 - eot)
    }

    /** The night portion used for adjusting times in higher latitudes */
    fun calculateNightPortion(method: HigherLatitudeMethod, angle: Double): Double {
        return when {
            method === HigherLatitudeMethod.ANGLEBASED -> angle / 60.0
            method === HigherLatitudeMethod.MIDNIGHT -> 0.5
            method === HigherLatitudeMethod.ONESEVENTH -> 0.14286
            else -> 0.0
        }
    }

    /** Convert hours to day portions */
    fun calculateDayPortion(times: DoubleArray): DoubleArray {
        for (i in 0..6) times[i] /= 24.0
        return times
    }

}