# Hijri Calendar Module

Converts between Gregorian and Hijri (Islamic) dates using the **Umm al-Qura** calendar.

> Valid range: **1356 AH** (14 March 1937 CE) to **1500 AH** (16 November 2077 CE)

## Usage

### Today's Hijri Date

```kotlin
val today = HijriCalendar.today
println(today)  // HijriDate(year=1446, month=10-Shawwal, day=12)

val yesterday = HijriCalendar.yesterday
val tomorrow = HijriCalendar.tomorrow
```

### Gregorian → Hijri

```kotlin
// From a Timestamp
val hijri = HijriCalendar.toHijri(Timestamp.now)

// Using the extension function
val hijri = Timestamp(LocalDate(2024, 10, 31)).toHijri()

// Batch conversion
val dates = listOf(Timestamp.yesterday, Timestamp.now, Timestamp.tomorrow)
val hijriDates = HijriCalendar.getHijriDates(dates)
```

### Hijri → Gregorian

```kotlin
val hijriDate = HijriDate(1446, 4, 7)  // 7 Rabi' al-Thani 1446
val gregorian = hijriDate.toGregorian()
println(gregorian)  // Timestamp{ 10 Oct 2024 12:00 am }
```

### Month Names

```kotlin
val hijri = HijriCalendar.today

// English (default)
println(hijri.longMonthName)   // "Shawwal"
println(hijri.shortMonthName)  // "Shw"

// Arabic
val arabicName = hijri.getMonthName(
    month = HijriCalendarMonthType.RAMADAN,
    locale = LocaleManager.Locales.ARABIC
)
println(arabicName)  // "رمضان"

// Short format
val shortName = hijri.getMonthName(
    month = HijriCalendarMonthType.RAMADAN,
    locale = LocaleManager.Locales.ENGLISH,
    format = HijriMonthNameFormat.SHORT
)
println(shortName)  // "Ram"
```

### Month & Year Length

```kotlin
// Days in a specific Hijri month (0-based month index)
val daysInRamadan = HijriCalendar.lengthOfMonth(hYear = 1446, hMonth = 8)
println(daysInRamadan)  // 30

// Days in a Hijri year
val daysInYear = HijriCalendar.lengthOfYear(1446)
println(daysInYear)  // 354 or 355
```

### Date Comparison

```kotlin
val date1 = HijriDate(1446, 8, 1)   // Ramadan 1
val date2 = HijriDate(1446, 9, 1)   // Shawwal 1

println(date1.isBefore(date2))  // true
println(date2.isAfter(date1))   // true
println(date1 == date2)         // false
```

## Models

### `HijriDate`

| Property | Type | Description |
|----------|------|-------------|
| `year` | `Int` | Hijri year |
| `monthNumber` | `Int` | Zero-based month index (0–11) |
| `day` | `Int` | Day of the month |
| `month` | `HijriMonth` | Month with name info |
| `longMonthName` | `String` | Full month name in English |
| `shortMonthName` | `String` | Abbreviated month name |

### `HijriCalendarMonthType`

All 12 Hijri months as enum values:

`MUHARRAM` · `SAFAR` · `RABI_AWWAL` · `RABI_THANI` · `JUMADA_AWWAL` · `JUMADA_THANI` · `RAJAB` · `SHAABAN` · `RAMADAN` · `SHAWWAL` · `THUL_QIDAH` · `THUL_HIJJAH`
