# Islamic Toolkit KMP

A **Kotlin Multiplatform** library providing essential Islamic utilities for Android and iOS applications.

## Features

| Module | Description |
|--------|-------------|
| 🕌 **Prayer Times** | Accurate prayer time calculations with 7 built-in methods |
| 📅 **Hijri Calendar** | Gregorian ↔ Hijri date conversion (Umm al-Qura) |
| 🧭 **Qibla** | Qibla direction calculation from any location |

## Supported Platforms

- **Android** (JVM 11+, minSdk 24)
- **iOS** (x64, arm64, Simulator arm64)

## Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
// In your shared module
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("com.typ:islamic-toolkit-kmp:<version>")
        }
    }
}
```

## Quick Start

### Prayer Times

```kotlin
val location = PopularLocations.Egypt.CAIRO
val config = prayerTimesCalcConfig {
    calcMethod = CalculationMethod.EGYPT
}

val times = PrayerTimes.getTodayPrays(location, config)

println(times.fajr.formattedTime)    // "04:23 am"
println(times.dhuhr.formattedTime)   // "11:52 am"
println(times.currentPray)           // Current active prayer
println(times.nextPray)              // Next upcoming prayer
```

See the full [Prayer Times Guide](src/commonMain/kotlin/com/typ/islamic_toolkit_kmp/praytimes/README.md).

### Hijri Calendar

```kotlin
// Today's Hijri date
val today = HijriCalendar.today
println(today)  // "HijriDate(year=1446, month=10-Shawwal, day=12)"

// Convert a specific Gregorian date
val hijri = Timestamp(LocalDate(2024, 10, 31)).toHijri()
println("${hijri.day} ${hijri.longMonthName} ${hijri.year}")

// Convert back to Gregorian
val gregorian = hijri.toGregorian()
```

See the full [Hijri Calendar Guide](src/commonMain/kotlin/com/typ/islamic_toolkit_kmp/hijri/README.md).

### Qibla Direction

```kotlin
// Get Qibla bearing in degrees from North (clockwise)
val bearing = Qibla.calculateQiblaBearing(
    locLat = 30.0444,  // Cairo latitude
    locLng = 31.2357   // Cairo longitude
)
println("Qibla: $bearing°")  // "Qibla: 135.5°"

// Get as angle components (degrees, minutes, seconds)
val angle = Qibla.calculateQiblaAngle(30.0444, 31.2357)
println("${angle.deg}° ${angle.min}' ${angle.sec}\"")
```

See the full [Qibla Guide](src/commonMain/kotlin/com/typ/islamic_toolkit_kmp/qibla/README.md).

## Configuration

### Calculation Methods

| Method | Region |
|--------|--------|
| `EGYPT` | Egyptian General Authority of Survey |
| `MAKKAH` | Umm al-Qura, Makkah |
| `MUSWL` | Muslim World League |
| `ISNA` | Islamic Society of North America |
| `KARACHI` | University of Islamic Sciences, Karachi |
| `JAFARI` | Ithna Ashari (Shia) |
| `TEHRAN` | Institute of Geophysics, University of Tehran |
| `CUSTOM` | User-defined parameters |

### Asr Methods

| Method | Description |
|--------|-------------|
| `SHAFII` | Shadow length = 1× object height (majority) |
| `HANAFI` | Shadow length = 2× object height |

### Higher Latitude Adjustments

| Method | Description |
|--------|-------------|
| `NONE` | No adjustment |
| `MIDNIGHT` | Middle of night |
| `ONESEVENTH` | 1/7th of night |
| `ANGLEBASED` | Angle/60th of night |

## Project Structure

```
src/
├── commonMain/          # Shared Kotlin code
│   └── kotlin/
│       └── com/typ/islamic_toolkit_kmp/
│           ├── core/        # Datetime, Location, Math utilities
│           ├── hijri/       # Hijri calendar conversion
│           ├── praytimes/   # Prayer times calculation
│           ├── qibla/       # Qibla direction calculation
│           └── shared/      # expect declarations (Locale, SimpleDateFormat)
├── androidMain/         # Android actual implementations
├── iosMain/             # iOS actual implementations
└── commonTest/          # Shared tests
```

## Dependencies

- [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) — Multiplatform date/time
- [kotlinx-datetime-ext](https://github.com/AACPanir/kotlinx-datetime-ext) — DateTime extensions

## License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

Copyright © 2026 Ahmed Sleem

## Author

**Ahmed Sleem** — [@typ-AhmedSleem](https://github.com/typ-AhmedSleem)
