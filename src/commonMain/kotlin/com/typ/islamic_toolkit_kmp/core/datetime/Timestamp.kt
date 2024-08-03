/*
 * This app is developed by AHMED SLEEM
 *
 * Copyright (c) 2021.  TYP INC. All Rights Reserved
 */
package com.typ.islamic_toolkit_kmp.core.datetime

import com.typ.islamic_toolkit_kmp.core.annotations.IntRange
import com.typ.islamic_toolkit_kmp.core.datetime.PatternFormatter.DateTimeFull
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * Class that holds and does all operations that has time and date on it.
 * It can...
 * 1.Get or Set a field.
 * 2.Get timestamp as Date object.
 * 3.Get timestamp as CalendarFields instance.
 * 4.Get formatted timestamp according to given pattern.
 * 5.Compare to another timestamp in date or time or both
 */
class Timestamp private constructor() {

    var instant: Instant = Clock.System.now()
        private set

    var timeZone: TimeZone = TimeZone.currentSystemDefault()
        set(value) {
            instant = instant.toLocalDateTime(timeZone).toInstant(timeZone)
            field = value
        }

    constructor(timestampInMillis: Long) : this() {
        instant = Instant.fromEpochMilliseconds(timestampInMillis)
    }

    constructor(date: LocalDate) : this() {
        instant = LocalDateTime(date, LocalTime(0, 0)).toInstant(timeZone)
    }

    constructor(time: LocalTime) : this() {
        instant = LocalDateTime(this.date, time).toInstant(timeZone)
    }

    constructor(date: LocalDate, time: LocalTime) : this() {
        instant = LocalDateTime(date, time).toInstant(timeZone)
    }

    val millis: Long
        get() = instant.toEpochMilliseconds()

    var date: LocalDate
        get() = instant.toLocalDateTime(timeZone).date
        set(newDate) {
            instant = LocalDateTime(newDate, this.time).toInstant(timeZone)
        }

    var time: LocalTime
        get() = instant.toLocalDateTime(timeZone).time
        set(newTime) {
            instant = LocalDateTime(this.date, newTime).toInstant(timeZone)
        }

    @get:IntRange(from = 0, to = 60)
    var seconds: Int
        get() = time.second
        set(value) {
            instant = instant.plus(value, DateTimeUnit.SECOND)
        }

    @get:IntRange(from = 0, to = 59)
    var minutes: Int
        get() = time.minute
        set(value) {
            instant = instant.plus(value, DateTimeUnit.MINUTE)
        }

    @get:IntRange(from = 0, to = 23)
    var hour: Int
        get() = time.hour
        set(value) {
            instant = instant.plus(value, DateTimeUnit.HOUR)
        }

    @get:IntRange(from = 1, to = 31)
    var day: Int
        get() = date.dayOfMonth
        set(value) {
            val curr = this.date
            val date = LocalDate(curr.year, curr.monthNumber, value)
            instant = LocalDateTime(date, this.time).toInstant(timeZone)
        }

    @get:IntRange(from = 1, to = 12)
    var month: Int
        get() = date.monthNumber
        set(value) {
            val curr = this.date
            val date = LocalDate(curr.year, value, curr.dayOfMonth)
            println("Settings month to $value. sum= ${curr.monthNumber + value}")
            instant = LocalDateTime(date, this.time).toInstant(timeZone)
        }

    var year: Int
        get() = date.year
        set(value) {
            val curr = this.date
            val date = LocalDate(value, curr.month, curr.dayOfMonth)
            instant = LocalDateTime(date, this.time).toInstant(timeZone)
        }

    /**
     * @apiNote First day of the week is MONDAY by default.
     * Monday is 1 and Sunday is 7
     */
    val dayOfWeek: DayOfWeek
        get() = date.dayOfWeek

    fun hasSameDayOf(another: Timestamp?): Boolean {
        return another?.let { day == another.day } ?: false
    }

    fun hasSameMonthOf(another: Timestamp?): Boolean {
        return another?.let { month == another.month } ?: false
    }

    fun hasSameYearOf(another: Timestamp?): Boolean {
        return another?.let { year == it.year } ?: false
    }

    fun matches(another: Timestamp?): Boolean {
        return another?.let { dateMatches(it) && timeMatches(it) } ?: false
    }

    fun dateMatches(another: Timestamp?): Boolean {
        return another?.let { (day == it.day) && (month == it.month) && (year == it.year) } ?: false
    }

    fun timeMatches(another: Timestamp?): Boolean {
        return another?.let { minutes == it.minutes && hour == it.hour } ?: false
    }

    fun isAfter(another: Timestamp?): Boolean {
        return another?.let { isAfter(it.millis) } ?: false
    }

    /**
     * Checks if the timestamp represented by this object is after the specified timestamp.
     *
     * This method compares the current timestamp, represented by the object calling the method,
     * with the provided timestamp in milliseconds since the epoch. It returns `true` if the current
     * timestamp is later (greater) than the specified timestamp, and `false` otherwise.
     *
     * @param timestamp The timestamp to compare with, specified in milliseconds since the epoch.
     * @return `true` if the current timestamp is later (greater) than the specified timestamp,
     *         `false` otherwise.
     */
    fun isAfter(timestamp: Long) = millis > timestamp

    fun isBefore(another: Timestamp?) = if (another == null) false else isBefore(another.millis)

    fun isBefore(timestamp: Long) = millis < timestamp

    fun getFormatted(pattern: PatternFormatter) = pattern.format(this)

    fun roll(field: CalendarField, amount: Int) {
        when (field) {
            CalendarField.YEAR -> year += amount
            CalendarField.MONTH -> month += amount
            CalendarField.DATE -> day += amount
            CalendarField.HOUR -> hour += amount
            CalendarField.MINUTES -> minutes += amount
            CalendarField.SECONDS -> seconds += amount
        }
    }

    operator fun set(field: CalendarField, newValue: Int) {
        when (field) {
            CalendarField.YEAR -> year = newValue
            CalendarField.MONTH -> month = newValue
            CalendarField.DATE -> day = newValue
            CalendarField.HOUR -> hour = newValue
            CalendarField.MINUTES -> minutes = newValue
            CalendarField.SECONDS -> seconds = newValue
        }
    }

    val isLastMonth: Boolean
        get() {
            val lastMonthTimestamp = now.apply { roll(CalendarField.MONTH, -1) }
            return hasSameYearOf(lastMonthTimestamp) && hasSameMonthOf(lastMonthTimestamp)
        }
    val isInThisMonth: Boolean
        get() = hasSameYearOf(now) && hasSameMonthOf(now)
    val isNextMonth: Boolean
        get() {
            val nextMonthTimestamp = now.apply { roll(CalendarField.MONTH, 1) }
            return hasSameYearOf(nextMonthTimestamp) && hasSameMonthOf(nextMonthTimestamp)
        }
    val isYesterday: Boolean
        get() = dateMatches(yesterday)
    val isToday: Boolean
        get() = dateMatches(now)
    val isTomorrow: Boolean
        get() = dateMatches(tomorrow)

    val previousDay: Timestamp
        get() = this.clone().apply { day -= 1 }

    val nextDay: Timestamp
        get() = this.clone().apply { day += 1 }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return if (other == null || this::class != other::class) false else matches(other as Timestamp?)
    }

    operator fun get(field: CalendarField): Int {
        return when (field) {
            CalendarField.YEAR -> year
            CalendarField.MONTH -> month
            CalendarField.DATE -> day
            CalendarField.HOUR -> hour
            CalendarField.MINUTES -> minutes
            CalendarField.SECONDS -> seconds
        }
    }

    fun clone(): Timestamp {
        return Timestamp(millis)
    }

    override fun hashCode(): Int {
        return minutes.hashCode() + hour.hashCode() + day.hashCode() + month.hashCode() + year.hashCode()
    }

    override fun toString() = "Timestamp{ " + getFormatted(DateTimeFull()) + " }"

    companion object {
        val yesterday: Timestamp
            get() = now.apply { roll(CalendarField.DATE, -1) }

        val now: Timestamp
            get() = Timestamp()

        val tomorrow
            get() = now.apply { roll(CalendarField.DATE, 1) }
    }
}
