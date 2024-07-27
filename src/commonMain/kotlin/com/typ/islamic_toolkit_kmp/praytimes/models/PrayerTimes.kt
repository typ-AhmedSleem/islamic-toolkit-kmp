package com.typ.islamic_toolkit_kmp.praytimes.models

import com.typ.islamic_toolkit_kmp.core.datetime.CalendarField
import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp
import com.typ.islamic_toolkit_kmp.core.location.Location
import com.typ.islamic_toolkit_kmp.praytimes.enums.PrayType
import com.typ.islamic_toolkit_kmp.praytimes.lib.PrayerTimesCalculator
import kotlinx.datetime.LocalTime
import kotlin.jvm.JvmStatic

class PrayerTimes private constructor(
    val fajr: Pray,
    val sunrise: Pray,
    val dhuhr: Pray,
    val asr: Pray,
    val maghrib: Pray,
    val isha: Pray
) {

    operator fun get(index: Int) = toArray()[index]

    operator fun contains(pray: Pray) = pray in toArray()

    operator fun iterator() = toArray().iterator()

    fun toArray() = arrayOf(fajr, sunrise, dhuhr, asr, maghrib, isha)

    fun toArrayNoSunrise() = arrayOf(fajr, dhuhr, asr, maghrib, isha)

    val currentPray: Pray
        get() = getCurrentPray(this)

    val nextPray: Pray?
        get() = getNextPray(this)

    val upcomingPrays: Array<Pray>
        get() = getUpcomingPrays(this)

    override fun toString(): String {
        return """
            PrayTimes{
                fajr=${fajr}
                sunrise=${sunrise}
                dhuhr=${dhuhr}
                asr=${asr}
                maghrib=${maghrib}
                isha=${isha}
            }
            """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PrayerTimes) return false

        if (fajr != other.fajr) return false
        if (sunrise != other.sunrise) return false
        if (dhuhr != other.dhuhr) return false
        if (asr != other.asr) return false
        if (maghrib != other.maghrib) return false
        return isha == other.isha
    }

    override fun hashCode(): Int {
        var result = fajr.hashCode()
        result = 31 * result + sunrise.hashCode()
        result = 31 * result + dhuhr.hashCode()
        result = 31 * result + asr.hashCode()
        result = 31 * result + maghrib.hashCode()
        result = 31 * result + isha.hashCode()
        return result
    }

    companion object {

        /** Helper method that clones given timestamp then applies
         * given HMS to it.
         *
         * NOTE: A KTX version of this method will be included in the next
         * update of this library in file: CoreKTX and known as: `Timestamp.byHMS(...)`
         *
         * @param timestamp Timestamp to be cloned.
         * @param hms Time components to be applied.
         *
         * @return New instance of [Timestamp] with time components applied.
         *
         */
        private fun timestampFromHMS(timestamp: Timestamp, time: LocalTime): Timestamp {
            return timestamp.clone().apply { this.time = time }
        }

        @JvmStatic
        fun getPrays(calculator: PrayerTimesCalculator, timestamp: Timestamp): PrayerTimes {
            return calculator.getPrayTimes(timestamp).run {
                PrayerTimes(
                    fajr = Pray(PrayType.FAJR, timestampFromHMS(timestamp.clone(), this[0])),
                    sunrise = Pray(PrayType.SUNRISE, timestampFromHMS(timestamp.clone(), this[1])),
                    dhuhr = Pray(PrayType.DHUHR, timestampFromHMS(timestamp.clone(), this[2])),
                    asr = Pray(PrayType.ASR, timestampFromHMS(timestamp.clone(), this[3])),
                    maghrib = Pray(PrayType.MAGHRIB, timestampFromHMS(timestamp.clone(), this[4])),
                    isha = Pray(PrayType.ISHA, timestampFromHMS(timestamp.clone(), this[5]))
                )
            }
        }

        @JvmStatic
        fun getPrays(location: Location, config: PrayerTimesCalculator.Config, timestamp: Timestamp): PrayerTimes {
            return getPrays(PrayerTimesCalculator(location, config), timestamp)
        }

        @JvmStatic
        fun getPrays(location: Location, config: PrayerTimesCalculator.Config, daysToRoll: Int): PrayerTimes {
            return getPrays(
                location,
                config,
                Timestamp.now.apply { roll(CalendarField.DATE, daysToRoll) }
            )
        }

        @JvmStatic
        fun getTodayPrays(calculator: PrayerTimesCalculator): PrayerTimes {
            return getPrays(calculator, Timestamp.now)
        }

        @JvmStatic
        fun getTodayPrays(location: Location, config: PrayerTimesCalculator.Config): PrayerTimes {
            return getPrays(location, config, Timestamp.now)
        }

        @JvmStatic
        fun getCurrentPray(prays: PrayerTimes): Pray {
            var currentPray: Pray = prays.fajr
            for (pray in prays.toArrayNoSunrise()) {
                if (pray.passed) currentPray = pray
                else break
            }
            return currentPray
        }

        @JvmStatic
        fun getNextPray(prays: PrayerTimes): Pray? {
            // Get the next pray in given prays
            if (prays.isha.passed) return null
            var nextPray = prays.fajr
            for (pray in prays) {
                if (pray.passed) continue
                nextPray = pray
                break
            }
            return nextPray
        }

        @JvmStatic
        fun getUpcomingPrays(prays: PrayerTimes): Array<Pray> {
            val upPrays = mutableListOf<Pray>()
            for (pray in prays) {
                if (pray.passed) continue
                upPrays.add(pray)
            }
            return upPrays.toTypedArray()
        }

    }
}
