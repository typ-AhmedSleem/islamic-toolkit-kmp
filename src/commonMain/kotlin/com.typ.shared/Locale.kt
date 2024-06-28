@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.typ.shared

expect class Locale(language: String = "", country: String = "") {
    val language: String
    val country: String

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int

    override fun toString(): String
}