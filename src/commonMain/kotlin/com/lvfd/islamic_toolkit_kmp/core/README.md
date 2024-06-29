## Core

#### Contains necessary code used by other modules

---

## Module structure

* [**core.locale**](https://github.com/typ-AhmedSleem/islamic-toolkit-kt/blob/develop/core/src/main/kotlin/com.typ.islamictkt/locale): Provides locale-related utils.

* [**core.location**](https://github.com/typ-AhmedSleem/islamic-toolkit-kt/core/blob/develop/src/main/kotlin/com.typ.islamictkt/location): Provides
    * `Location` class to hold location information.
    * `PopularLocations` object where you can find most popular cities
      as location models ready to be used.

* [**core.datetime**](https://github.com/typ-AhmedSleem/islamic-toolkit-kt/blob/develop/core/src/main/kotlin/com.typ.islamictkt/datetime): Provides
    * Inheritable classes to easily handle datetime processing and representations.
    * Multiple high-level datetime format patterns as classes.

---

## Usage Examples (Kotlin)

### core.locale

Getting default locale of the system

```kotlin
/** it typically calls: Locale.getDefault()
 *  but is added as a refrence to future work in LocaleManager
 */
val locale = LocaleManager.getDefault()
```

Get a missing locale in `java.util.Locale`

```kotlin
val locale = LocaleManager.Locales.ARABIC
```

or create using custom language code

```kotlin
// Creates a Locale for Arabic
val locale = LocaleManager.custom("ar")
```

### core.datetime

Get a pre-defined `PatternFormatter`

```kotlin
val formatter = PatternFormatter.Time12SX() // 06:03 am
```

or create a custom formatter

```kotlin
val formatter = PatternFormatter.custom("dd MMM yyyy") // 01 Aug 2001
```

> #### Tired of raw time in milliseconds, well say hello to `Timestamp`
> `Timestamp` object instance takes a snapshot of system datetime
> by getting calendar instance using `Calendar.getInstance()`.

```kotlin
val now = Timestamp.now // timestamp of Now.
val yesterday = Timestamp.yesterday // timestamp of Yesterday. [see NOTE below]
val tomorrow = Timestamp.tomorrow // timestamp of Tomorrow. [see NOTE below]
```

> **NOTE:** `Timestamp.yesterday` or `Timestamp.tomorrow` takes a datetime
> snapshot of now using `Timestamp.now` then rolls date by 1 or -1
> > `fun tomorrow() = now().apply { roll(Calendar.DATE, 1) }`
>
> > `fun yesterday() = now().apply { roll(Calendar.DATE, -1) }`
>

### core.location

Creating a new `Location`

```kotlin
val cairo = Location(
    code = "EG",
    latitude = 30.12367823,
    longitude = 31.25339501,
    timezone = 2.0
)
```

or you can use a pre-defined city from `PopularLocations`
> **NOTE:** Not all cities are defined in `PopularLocations`
> and more cities are to be added later by me or open-source contributors.

```kotlin
val cairo = PopularLocations.Egypt.CAIRO
```