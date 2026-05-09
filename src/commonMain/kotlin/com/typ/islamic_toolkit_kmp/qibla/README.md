# Qibla Module

Calculates the Qibla direction (bearing toward the Kaaba in Makkah) from any location on Earth.

## Usage

### Qibla Bearing

Returns the angle in degrees from **North** (clockwise):

```kotlin
val bearing = Qibla.calculateQiblaBearing(
    locLat = 30.0444,  // Cairo latitude
    locLng = 31.2357   // Cairo longitude
)
println("Qibla: $bearing°")
// Output: "Qibla: 135.5°"
```

**Direction reference:**

| Degrees | Direction |
|---------|-----------|
| 0° | North |
| 90° | East |
| 180° | South |
| 270° | West |

### Qibla Angle (DMS)

Returns the bearing as degrees, minutes, and seconds:

```kotlin
val angle = Qibla.calculateQiblaAngle(
    lat = 30.0444,
    lng = 31.2357
)
println("${angle.deg}° ${angle.min}' ${angle.sec}\"")
// Output: "135° 30' 12.5"
```

### Examples by City

```kotlin
// Cairo, Egypt → Southeast
Qibla.calculateQiblaBearing(30.0444, 31.2357)   // ~135°

// New York, USA → Northeast
Qibla.calculateQiblaBearing(40.7128, -74.0060)  // ~58°

// Jakarta, Indonesia → Northwest
Qibla.calculateQiblaBearing(-6.2088, 106.8456)  // ~295°

// London, UK → Southeast
Qibla.calculateQiblaBearing(51.5074, -0.1278)   // ~119°
```

## Models

### `Angle`

| Property | Type | Description |
|----------|------|-------------|
| `deg` | `Int` | Degrees component |
| `min` | `Int` | Minutes component |
| `sec` | `Double` | Seconds component |

Construction from a decimal angle:

```kotlin
val angle = Angle(angle = 135.5, radians = false)
println(angle.deg)  // 135
println(angle.min)  // 30
println(angle.sec)  // 0.0
```
