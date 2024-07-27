package com.typ.islamic_toolkit_kmp.praytimes.lib

class CalculationMethodParameters(
    var fajrAngle: Double,
    var shouldApplyMaghribMinutes: Boolean,
    maghribAngle: Double,
    maghribMinutes: Double,
    var shouldApplyIshaMinutes: Boolean,
    ishaAngle: Double,
    ishaMinutes: Double
) {

    var maghribAngle: Double = maghribAngle
        set(value) {
            field = value
            shouldApplyMaghribMinutes = false
        }

    var maghribMinutes: Double = maghribMinutes
        set(value) {
            field = value
            shouldApplyMaghribMinutes = true
        }

    var ishaAngle: Double = ishaAngle
        set(value) {
            field = value
            shouldApplyIshaMinutes = false
        }

    var ishaMinutes: Double = ishaMinutes
        set(value) {
            field = value
            shouldApplyIshaMinutes = true
        }

    override fun toString(): String {
        return "CalculationMethodParameters(applyMaghribMinutes=$shouldApplyMaghribMinutes, applyIshaMinutes=$shouldApplyIshaMinutes, fajrAngle=$fajrAngle, maghribAngle=$maghribAngle, maghribMinutes=$maghribMinutes, ishaAngle=$ishaAngle, ishaMinutes=$ishaMinutes)"
    }

}