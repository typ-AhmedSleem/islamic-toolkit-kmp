package com.typ.islamic_toolkit_kmp.praytimes.utils

import com.typ.islamic_toolkit_kmp.praytimes.enums.CalculationMethod
import com.typ.islamic_toolkit_kmp.praytimes.lib.CalculationMethodParameters
import com.typ.islamic_toolkit_kmp.praytimes.lib.PrayerTimesCalculator
import com.typ.islamic_toolkit_kmp.praytimes.lib.PrayerTimesOffsets

inline fun prayerTimesCalcConfig(config: PrayerTimesCalculator.Config.() -> Unit): PrayerTimesCalculator.Config {
    return PrayerTimesCalculator.Config().apply(config)
}

inline fun prayerTimesOffsets(offsets: PrayerTimesOffsets.() -> Unit): PrayerTimesOffsets {
    return PrayerTimesOffsets().apply(offsets)
}

inline fun calcMethodParameters(params: CalculationMethodParameters.() -> Unit): CalculationMethodParameters {
    return CalculationMethod.CUSTOM.parameters.apply(params)
}

val zeroOffsets: PrayerTimesOffsets
    get() = PrayerTimesOffsets()

fun PrayerTimesOffsets.zeros(): PrayerTimesOffsets {
    return this.apply {
        fajr = 0
        sunrise = 0
        dhuhr = 0
        asr = 0
        maghrib = 0
        isha = 0
    }
}