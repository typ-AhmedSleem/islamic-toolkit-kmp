# Prayer Times Module

Calculates Islamic prayer times for any location using multiple calculation methods.

## Usage

### Basic — Today's Prayer Times

```kotlin
val location = PopularLocations.Egypt.CAIRO
val config = prayerTimesCalcConfig {
    calcMethod = CalculationMethod.EGYPT
}

val times = PrayerTimes.getTodayPrays(location, config)

// Access individual prayers
println(times.fajr.formattedTime)     // "04:23 am"
println(times.sunrise.formattedTime)  // "05:52 am"
println(times.dhuhr.formattedTime)    // "11:52 am"
println(times.asr.formattedTime)      // "03:18 pm"
println(times.maghrib.formattedTime)  // "05:42 pm"
println(times.isha.formattedTime)     // "07:05 pm"
```

### Specific Date

```kotlin
val date = Timestamp(LocalDate(2024, 10, 31))
val times = PrayerTimes.getPrays(location, config, date)
```

### Prayer Status

```kotlin
val times = PrayerTimes.getTodayPrays(location, config)

// Current and next prayer
val current = times.currentPray   // The active prayer
val next = times.nextPray         // Next upcoming prayer (nullable)

// Check if all prayers are done for today
if (times.todayPraysFinished) {
    println("All prayers completed. Next Fajr: ${times.fajrNextDay.formattedTime}")
}

// Filter upcoming prayers
val upcoming = times.upcomingPrays
```

### Iterate Over Prayers

```kotlin
// All 6 prayers (Fajr, Sunrise, Dhuhr, Asr, Maghrib, Isha)
for (pray in times) {
    println("${pray.type}: ${pray.formattedTime} — ${pray.status}")
}

// Obligatory prayers only (no Sunrise)
times.listedNoSunrise.forEach { pray ->
    println("${pray.type}: ${pray.formattedTime}")
}
```

### Custom Formatting

```kotlin
val pray = times.fajr

// 24-hour format
val time24 = pray.getFormattedTime(PatternFormatter.Time24(), LocaleManager.Locales.ENGLISH)
// "04:23"

// Arabic locale
val timeAr = pray.getFormattedTime(PatternFormatter.PrayTimes(), LocaleManager.Locales.ARABIC)
// "٠٤:٢٣ ص"
```

## Configuration

### Calculation Method

```kotlin
val config = prayerTimesCalcConfig {
    calcMethod = CalculationMethod.EGYPT    // Default for Egypt
    // Available: JAFARI, KARACHI, ISNA, MUSWL, MAKKAH, EGYPT, TEHRAN, CUSTOM
}
```

### Asr Method

```kotlin
val config = prayerTimesCalcConfig {
    calcMethod = CalculationMethod.EGYPT
    asrMethod = AsrMethod.SHAFII   // Default: shadow = 1x object height
    // or: AsrMethod.HANAFI        // shadow = 2x object height
}
```

### Higher Latitude Adjustment

For locations at extreme latitudes where twilight may persist:

```kotlin
val config = prayerTimesCalcConfig {
    calcMethod = CalculationMethod.MUSWL
    higherLatMethod = HigherLatitudeMethod.ANGLEBASED
    // Options: NONE, MIDNIGHT, ONESEVENTH, ANGLEBASED
}
```

### Time Offsets

Fine-tune individual prayer times (in minutes):

```kotlin
val config = prayerTimesCalcConfig {
    calcMethod = CalculationMethod.EGYPT
    offsets = prayerTimesOffsets {
        fajr = -2      // 2 minutes earlier
        sunrise = 0
        dhuhr = 1      // 1 minute later
        asr = 0
        maghrib = 3    // 3 minutes later
        isha = 0
    }
}
```

### Custom Calculation Parameters

```kotlin
val config = prayerTimesCalcConfig {
    calcMethod = CalculationMethod.CUSTOM
    // Then set custom parameters directly:
}

val customParams = calcMethodParameters {
    fajrAngle = 18.5
    ishaAngle = 17.0
    maghribMinutes = 0.0
}
```

## Models

### `Pray`

| Property | Type | Description |
|----------|------|-------------|
| `type` | `PrayType` | `FAJR`, `SUNRISE`, `DHUHR`, `ASR`, `MAGHRIB`, `ISHA` |
| `time` | `Timestamp` | The prayer time |
| `status` | `PrayStatus` | `PASSED`, `CURRENT`, `UPCOMING` |
| `formattedTime` | `String` | Pre-formatted time string (`"04:23 am"`) |

### `PrayerTimes`

| Property | Type | Description |
|----------|------|-------------|
| `fajr` | `Pray` | Fajr prayer |
| `sunrise` | `Pray` | Sunrise time |
| `dhuhr` | `Pray` | Dhuhr prayer |
| `asr` | `Pray` | Asr prayer |
| `maghrib` | `Pray` | Maghrib prayer |
| `isha` | `Pray` | Isha prayer |
| `fajrNextDay` | `Pray` | Next day's Fajr |
| `currentPray` | `Pray` | Currently active prayer |
| `nextPray` | `Pray?` | Next upcoming prayer |
| `todayPraysFinished` | `Boolean` | Whether all prayers have passed |
