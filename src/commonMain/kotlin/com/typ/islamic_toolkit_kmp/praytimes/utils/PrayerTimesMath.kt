package com.lvfd.islamic_toolkit_kmp.praytimes.utils

import com.lvfd.islamic_toolkit_kmp.core.math.Math
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.tan

object PrayerTimesMath {

    /**
     * Range reduce angle in degrees.
     */
    fun finAngle(a: Double): Double {
        var temp = a
        temp -= 360 * floor(temp / 360.0)
        temp = if (temp < 0) temp + 360 else temp
        return temp
    }

    /**
     * Convert given angle from radians to degrees.
     */
    fun toDegrees(rAngle: Double) = Math.toDegrees(rAngle)

    /**
     * Convert given angle from degrees to radians.
     */
    fun toRadians(dAngle: Double) = Math.toRadians(dAngle)

    /**
     * Degree Sin
     */
    fun dSin(d: Double) = sin(toRadians(d))

    /**
     * Degree Cos
     */
    fun dCos(d: Double) = cos(toRadians(d))

    /**
     * Degree Tan
     */
    fun dTan(d: Double) = tan(toRadians(d))

    /**
     * Degree ArcSin
     */
    fun dArcSin(x: Double) = toDegrees(asin(x))

    /**
     * Degree ArcCos
     */
    fun dArcCos(x: Double) = toDegrees(acos(x))

    /**
     * Degree ArcTan
     */
    fun dArcTan(x: Double) = toDegrees(atan(x))

    /**
     * Degree ArcTan2
     */
    fun dArcTan2(y: Double, x: Double) = toDegrees(atan2(y, x))

    /**
     * Degree ArcCot
     */
    fun dArcCot(x: Double) = toDegrees(atan2(1.0, x))

}