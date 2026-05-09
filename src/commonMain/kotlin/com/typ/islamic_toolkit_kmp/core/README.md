# Core Module

Shared utilities used by all feature modules in the library.

## Components

### Timestamp

A wrapper around `kotlinx.datetime.Instant` for date/time operations.

```kotlin
// Current time
val now = Timestamp.now
val yesterday = Timestamp.yesterday
val tomorrow = Timestamp.tomorrow

// From a specific date
val date = Timestamp(LocalDate(2024, 10, 31))

// From date + time
val dateTime = Timestamp(
    date = LocalDate(2024, 10, 31),
    time = LocalTime(15, 30)
)

// Access fields
println(now.year)       // 2024
println(now.month)      // 10
println(now.day)        // 31
println(now.hour)       // 15
println(now.minutes)    // 30

// Navigate
val nextDay = now.nextDay
val prevDay = now.previousDay

// Roll by amount
now.roll(CalendarField.DATE, 7)   // Advance 7 days
now.roll(CalendarField.MONTH, -1) // Go back 1 month

// Format
println(now.getFormatted(PatternFormatter.DateFull()))
// Output: "31 October 2024"
```

### PatternFormatter

Pre-defined and custom date/time format patterns.

| Formatter | Pattern | Example |
|-----------|---------|---------|
| `Time12SX()` | `hh:mm aa` | `03:25 pm` |
| `Time12NSX()` | `hh:mm` | `03:25` |
| `Time24()` | `HH:mm` | `15:30` |
| `DateShort()` | `dd/MM/yyyy` | `01/08/2001` |
| `DateNormal()` | `dd MMM yyyy` | `01 Aug 2001` |
| `DateFull()` | `dd MMMM yyyy` | `01 August 2001` |
| `DateTimeFull()` | `dd MMM yyyy hh:mm aa` | `01 Aug 2001 12:03 am` |
| `PrayTimes()` | `hh:mm aa` | `05:23 am` |

```kotlin
// Custom pattern
val formatter = PatternFormatter.custom("yyyy-MM-dd HH:mm:ss")
println(formatter.format(Timestamp.now))
```

### Location

Represents a geographic location for calculations.

```kotlin
// Create a location
val cairo = Location(
    code = "EG",
    latitude = 30.0444,
    longitude = 31.2357,
    timezone = 2.0
)

// Or use a pre-defined city
val makkah = PopularLocations.SaudiArabia.MAKKAH
val dubai = PopularLocations.UnitedArabEmirates.DUBAI
```

### Available Cities in `PopularLocations`

| Country | Cities |
|---------|--------|
| 🇪🇬 Egypt | Cairo, Giza, Alexandria, Sohag, Luxor, Mersa Matruh |
| 🇸🇦 Saudi Arabia | Makkah, Medina |
| 🇦🇪 UAE | Dubai |
| 🇸🇾 Syria | Damascus |
| 🇵🇸 Palestine | Jerusalem |
| 🇮🇶 Iraq | Baghdad |
| 🇲🇦 Morocco | Casablanca |
| 🇩🇿 Algeria | Algiers |
| 🇰🇼 Kuwait | Kuwait City |
| 🇯🇴 Jordan | Amman |
| 🇱🇾 Libya | Tripoli |
| 🇱🇧 Lebanon | Beirut |
| 🇾🇪 Yemen | Sana'a |
| 🇶🇦 Qatar | Doha |
| 🇸🇩 Sudan | Khartoum |

### LocaleManager

Provides locale utilities for formatting.

```kotlin
val arabic = LocaleManager.Locales.ARABIC
val english = LocaleManager.Locales.ENGLISH
val custom = LocaleManager.custom("fr")
```