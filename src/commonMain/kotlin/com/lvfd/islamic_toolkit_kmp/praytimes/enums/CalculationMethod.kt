package com.lvfd.islamic_toolkit_kmp.praytimes.enums

import com.lvfd.islamic_toolkit_kmp.praytimes.lib.CalculationMethodParameters

/**
 * Calculation Method used during calculating PrayTimes.
 *
 * NOTE: This enum class and its parameters shouldn't be touched
 * or modified because that will cause in wrong calculations.
 *
 * Also in future development, this enum class is intended to be
 * converted to a sealed class to be extended to other types for
 * other developers to add with its own custom parameters so, if
 * you want to use custom method, assign the method in your
 * PrayerTimesCalculator instance to CUSTOM then assign
 * your custom parameters by calling inline fun '`calcMethodParameters {}`'
 * or by creating a new instance of '`CalculationMethodParameters`'
 *
 * @see CalculationMethodParameters
 *
 */
enum class CalculationMethod {
    /**
     * Ithna Ashari
     */
    JAFARI,

    /**
     * University of Islamic Sciences, Karachi
     */
    KARACHI,

    /**
     * Islamic Society of North America (ISNA)
     */
    ISNA,

    /**
     * Muslim World League (MWL)
     */
    MUSWL,

    /**
     * Umm al-Qura, Makkah
     */
    MAKKAH,

    /**
     * Egyptian General Authority of Survey
     */
    EGYPT,

    /**
     * Institute of Geophysics, University of Tehran
     */
    TEHRAN,

    /**
     * Custom Setting
     */
    CUSTOM;

    /**
     * fa : fajr angle.
     * ms : (0 = angle; 1 = minutes after sunset).
     * mv : (in angle or minutes)
     * is : (0 = angle; 1 = minutes after maghrib)
     * iv : (in angle or minutes)
     */
    val parameters: CalculationMethodParameters
        get() = when (this) {
            JAFARI -> CalculationMethodParameters(
                fajrAngle = 16.0,
                maghribAngle = 4.0,
                maghribMinutes = 0.0,
                ishaAngle = 14.0,
                ishaMinutes = 0.0,
                shouldApplyMaghribMinutes = false,
                shouldApplyIshaMinutes = false
            )

            KARACHI -> CalculationMethodParameters(
                fajrAngle = 18.0,
                maghribAngle = 0.0,
                maghribMinutes = 0.0,
                ishaAngle = 18.0,
                ishaMinutes = 0.0,
                shouldApplyMaghribMinutes = true,
                shouldApplyIshaMinutes = false
            )

            ISNA -> CalculationMethodParameters(
                fajrAngle = 15.0,
                maghribAngle = 0.0,
                maghribMinutes = 0.0,
                ishaAngle = 15.0,
                ishaMinutes = 0.0,
                shouldApplyMaghribMinutes = true,
                shouldApplyIshaMinutes = false
            )

            MUSWL -> CalculationMethodParameters(
                fajrAngle = 18.0,
                maghribAngle = 0.0,
                maghribMinutes = 0.0,
                ishaAngle = 17.0,
                ishaMinutes = 0.0,
                shouldApplyMaghribMinutes = true,
                shouldApplyIshaMinutes = false
            )

            MAKKAH -> CalculationMethodParameters(
                fajrAngle = 18.5,
                maghribAngle = 0.0,
                maghribMinutes = 0.0,
                ishaAngle = 0.0,
                ishaMinutes = 90.0,
                shouldApplyMaghribMinutes = true,
                shouldApplyIshaMinutes = true
            )

            EGYPT -> CalculationMethodParameters(
                fajrAngle = 19.5,
                maghribAngle = 0.0,
                maghribMinutes = 0.0,
                ishaAngle = 17.5,
                ishaMinutes = 0.0,
                shouldApplyMaghribMinutes = true,
                shouldApplyIshaMinutes = false
            )

            TEHRAN -> CalculationMethodParameters(
                fajrAngle = 17.7,
                maghribAngle = 4.5,
                maghribMinutes = 0.0,
                ishaAngle = 14.0,
                ishaMinutes = 0.0,
                shouldApplyMaghribMinutes = false,
                shouldApplyIshaMinutes = false
            )

            else -> CalculationMethodParameters(
                fajrAngle = 0.0,
                maghribAngle = 0.0,
                maghribMinutes = 0.0,
                ishaAngle = 0.0,
                ishaMinutes = 0.0,
                shouldApplyMaghribMinutes = false,
                shouldApplyIshaMinutes = false
            )
        }

    override fun toString(): String {
        return "CalculationMethod(name= ${this.name})"
    }

}
