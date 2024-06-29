package com.lvfd.islamic_toolkit_kmp.qibla

import com.lvfd.islamic_toolkit_kmp.core.math.Math
import kotlin.jvm.JvmStatic
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

/**
 * Class used to calculate Qibla angle for given location
 * coordinates (latitude & longitude)
 *
 * Part of code is inspired by iclib developed by fikr4n
 *
 * @author typ-AhmedSleem
 */
object Qibla {

    /**
     * Calculate the Qibla angle bearing in degrees
     * from the north (clock-wise).
     *
     * @param locLat Latitude of location in degrees
     * @param locLng Longitude of location in degrees
     * @return  Qibla angle in degrees
     * <ul>
     *     <li> 0 is north
     *     <li> 90 is east
     *     <li> 180 is south
     *     <li> 270 is west
     * </ul>
     * @see Qibla.calculateQiblaAngle
     */
    @JvmStatic
    fun calculateQiblaBearing(locLat: Double, locLng: Double): Double {
        val latMakkah = 21.422487
        val lngMakkah = 39.826206
        val bearing = atan2(
            y = sin((lngMakkah - locLng).rad()),
            x = cos(locLat.rad()) * tan(latMakkah.rad()) - sin(locLat.rad()) * cos((lngMakkah - locLng).rad())
        ).deg()
        return if (bearing >= 0) bearing else bearing + 360
    }

    /**
     * Calculate the Qibla angle for given location
     * in terms of angle components (degree, minute, seconds)
     * @return Angle components object
     *
     * @see Angle
     * @see Qibla.calculateQiblaBearing
     */
    @JvmStatic
    fun calculateQiblaAngle(lat: Double, lng: Double): Angle {
        return Angle(angle = calculateQiblaBearing(lat, lng), radians = false)
    }

    /* Math ext functions */
    private fun Double.rad() = Math.toRadians(this)
    private fun Double.deg() = Math.toDegrees(this)

}