package com.lvfd.islamic_toolkit_kmp.praytimes.enums

/**
 * HigherLatitude used during calculating PrayTimes in high-latitude locations
 */
enum class HigherLatitudeMethod {
    /**
     * No Adjustments
     */
    NONE,

    /**
     * Middle of Night
     */
    MIDNIGHT,

    /**
     * 1/7th of Night
     */
    ONESEVENTH,

    /**
     * Angle/60th of night
     */
    ANGLEBASED;
}
