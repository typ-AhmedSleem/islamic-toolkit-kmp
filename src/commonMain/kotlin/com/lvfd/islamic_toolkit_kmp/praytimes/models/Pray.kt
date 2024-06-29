package com.lvfd.islamic_toolkit_kmp.praytimes.models

import com.lvfd.islamic_toolkit_kmp.core.datetime.PatternFormatter
import com.lvfd.islamic_toolkit_kmp.core.datetime.Timestamp
import com.lvfd.islamic_toolkit_kmp.core.locale.LocaleManager
import com.lvfd.islamic_toolkit_kmp.praytimes.enums.PrayStatus
import com.lvfd.islamic_toolkit_kmp.praytimes.enums.PrayType
import com.lvfd.islamic_toolkit_kmp.shared.Locale
import kotlin.jvm.JvmField

/**
 * Model class of Pray which holds all Pray Item data.
 *
 * NOTE: This class can be extended to add more fields as you need.
 */
class Pray(
    /**
     * Used only to indicate what this pray is by enum ordinal
     */
    @JvmField val type: PrayType,
    /**
     * Timestamp of this pray time
     */
    @JvmField val time: Timestamp
) {

    constructor(type: PrayType, timeInMillis: Long) : this(type, Timestamp(timeInMillis))

    val status: PrayStatus
        get() {
            return if (time.isBefore(Timestamp.now)) PrayStatus.PASSED
            else PrayStatus.UPCOMING
        }

    val passed: Boolean
        get() = (status == PrayStatus.PASSED)

    val upcoming: Boolean
        get() = (status == PrayStatus.UPCOMING)

    val formattedTime: String
        get() = PatternFormatter.PrayTimes().format(time)

    fun getFormattedTime(formatter: PatternFormatter, locale: Locale): String {
        return formatter.format(time, locale)
    }

    fun getFormattedTime(formatter: PatternFormatter): String {
        return formatter.format(time, LocaleManager.getDefault())
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (this === other) return true
        return if (other !is Pray) false else type === other.type && time.matches(other.time)
    }

    override fun hashCode() = type.hashCode() + (time.millis * 0.001).toInt()
    override fun toString(): String {
        return buildString {
            append("Pray(type=")
            append(type)
            append(", time=")
            append(formattedTime)
            append(", status=")
            append(status)
            append(", passed=")
            append(passed)
            append(", upcoming=")
            append(upcoming)
            append(", datetime='")
            append(time.getFormatted(PatternFormatter.DateTimeFull()))
            append("')")
        }
    }


}
