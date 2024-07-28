package com.typ.islamic_toolkit_kmp.qibla

import com.typ.islamic_toolkit_kmp.core.math.Math

open class Angle {

    val deg: Int
    val min: Int
    val sec: Double

    constructor(deg: Int, min: Int, sec: Double) {
        this.deg = deg
        this.min = min
        this.sec = sec
    }

    constructor(angle: Double, radians: Boolean = false) {
        var degree = angle
        if (radians) degree = Math.toDegrees(angle)
        var sec = degree * 3600
        this.deg = degree.toInt()
        this.min = (sec % 3600 / 60).toInt()
        sec %= 60
        this.sec = sec % 60
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Angle) return false

        if (deg != other.deg) return false
        if (min != other.min) return false
        if (sec != other.sec) return false

        return true
    }

    override fun hashCode(): Int {
        var result = deg
        result = 31 * result + min
        result = 31 * result + sec.hashCode()
        return result
    }

    override fun toString(): String {
        return "Angle(deg=$deg, min=$min, sec=$sec)"
    }


}
