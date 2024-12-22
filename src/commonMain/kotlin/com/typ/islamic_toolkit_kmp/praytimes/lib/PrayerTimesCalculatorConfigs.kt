package com.typ.islamic_toolkit_kmp.praytimes.lib

import com.typ.islamic_toolkit_kmp.core.location.Location
import com.typ.islamic_toolkit_kmp.praytimes.enums.AsrMethod
import com.typ.islamic_toolkit_kmp.praytimes.enums.CalculationMethod
import com.typ.islamic_toolkit_kmp.praytimes.enums.HigherLatitudeMethod

object PrayerTimesCalculatorConfigs {

    data object MWL : PrayerTimesCalculator.Config(
        calcMethod = CalculationMethod.MUSWL,
        asrMethod = AsrMethod.SHAFII,
        higherLatMethod = HigherLatitudeMethod.MIDNIGHT,
    )

    data object ISNA : PrayerTimesCalculator.Config(
        calcMethod = CalculationMethod.ISNA,
        asrMethod = AsrMethod.SHAFII,
        higherLatMethod = HigherLatitudeMethod.MIDNIGHT,
    )

    data object KARACHI : PrayerTimesCalculator.Config(
        calcMethod = CalculationMethod.KARACHI,
        asrMethod = AsrMethod.HANAFI,
        higherLatMethod = HigherLatitudeMethod.MIDNIGHT,
    )

    data object MAKKAH : PrayerTimesCalculator.Config(
        calcMethod = CalculationMethod.MAKKAH,
        asrMethod = AsrMethod.SHAFII,
        higherLatMethod = HigherLatitudeMethod.MIDNIGHT,
    )

    data object EGYPT : PrayerTimesCalculator.Config(
        calcMethod = CalculationMethod.EGYPT,
        asrMethod = AsrMethod.SHAFII,
        higherLatMethod = HigherLatitudeMethod.ONESEVENTH,
    )

    data object TEHRAN : PrayerTimesCalculator.Config(
        calcMethod = CalculationMethod.TEHRAN,
        asrMethod = AsrMethod.SHAFII,
        higherLatMethod = HigherLatitudeMethod.ONESEVENTH,
    )

    data object CUSTOM : PrayerTimesCalculator.Config(
        calcMethod = CalculationMethod.CUSTOM,
        asrMethod = AsrMethod.SHAFII,
        higherLatMethod = HigherLatitudeMethod.NONE,
    )

    fun pickSuitableConfigForLocation(location: Location): PrayerTimesCalculator.Config {
        return when (location.code.trim().uppercase()) {
            in listOf("CA", "USA", "MEX") -> ISNA
            in listOf("PAK", "AFG", "BAN") -> KARACHI
            in listOf("KSA", "UAE", "KUW", "BAH", "QAT", "OMN", "YEM") -> MAKKAH
            in listOf("EG", "LIB", "ALG", "TUN", "MOR", "SOM") -> EGYPT
            in listOf("IRN", "IRA", "IR") -> TEHRAN // Iran
            else -> MWL
        }
    }

}