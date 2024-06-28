package com.typ.islamic_toolkit_kmp.core.math

object Math {

    const val PI = kotlin.math.PI

    fun toDegrees(radians: Double) = radians * 180.0 / PI

    fun toRadians(degrees: Double) = degrees * PI / 180.0
}