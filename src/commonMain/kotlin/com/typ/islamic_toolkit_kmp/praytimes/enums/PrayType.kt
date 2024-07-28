package com.typ.islamic_toolkit_kmp.praytimes.enums

enum class PrayType {
    FAJR,
    SUNRISE,
    DHUHR,
    ASR,
    MAGHRIB,
    ISHA;

    val ordinalWithoutSunrise: Int
        get() = if (this == FAJR) 0 else ordinal - 1
}
