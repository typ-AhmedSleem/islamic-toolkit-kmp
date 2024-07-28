package com.typ.islamic_toolkit_kmp.shared

expect class Locale(language: String = "", country: String = "") {
    val language: String
    val country: String

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int

    override fun toString(): String
}