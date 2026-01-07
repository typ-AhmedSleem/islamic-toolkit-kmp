package com.typ.islamic_toolkit_kmp.praytimes.extensions

import com.typ.islamic_toolkit_kmp.core.location.Location
import com.typ.islamic_toolkit_kmp.praytimes.extensions.ConfigRegions.regionsEgypt
import com.typ.islamic_toolkit_kmp.praytimes.extensions.ConfigRegions.regionsISNA
import com.typ.islamic_toolkit_kmp.praytimes.extensions.ConfigRegions.regionsKarachi
import com.typ.islamic_toolkit_kmp.praytimes.extensions.ConfigRegions.regionsMakkah
import com.typ.islamic_toolkit_kmp.praytimes.extensions.ConfigRegions.regionsTehran
import com.typ.islamic_toolkit_kmp.praytimes.lib.PrayerTimesCalculator
import com.typ.islamic_toolkit_kmp.praytimes.lib.PrayerTimesCalculatorConfigs

internal object ConfigRegions {
    internal val regionsISNA = listOf("CA", "USA", "MEX")
    internal val regionsKarachi = listOf("PAK", "AFG", "BAN")
    internal val regionsMakkah = listOf("KSA", "UAE", "KUW", "BAH", "QAT", "OMN", "YEM")
    internal val regionsEgypt = listOf("EG", "LIB", "ALG", "TUN", "MOR", "SOM")
    internal val regionsTehran = listOf("IRN", "IRA", "IR")
}

fun pickSuitableConfigForLocation(location: Location): PrayerTimesCalculator.Config {
    return when (location.code.trim().uppercase()) {
        in regionsISNA -> PrayerTimesCalculatorConfigs.ISNA
        in regionsKarachi -> PrayerTimesCalculatorConfigs.KARACHI
        in regionsMakkah -> PrayerTimesCalculatorConfigs.MAKKAH
        in regionsEgypt -> PrayerTimesCalculatorConfigs.EGYPT
        in regionsTehran -> PrayerTimesCalculatorConfigs.TEHRAN
        else -> PrayerTimesCalculatorConfigs.MWL
    }
}

fun Location.pickSuitableConfig(): PrayerTimesCalculator.Config {
    return pickSuitableConfigForLocation(this)
}