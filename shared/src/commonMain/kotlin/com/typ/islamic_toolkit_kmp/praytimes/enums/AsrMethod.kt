package com.typ.islamic_toolkit_kmp.praytimes.enums

// todo: Must be documented
enum class AsrMethod {

    /**
     * Shafii
     */
    SHAFII,

    /**
     * Hanafi
     */
    HANAFI;

    val shadowLength: Double
        get() = when (this) {
            SHAFII -> 1.0
            HANAFI -> 2.0
        }

}
