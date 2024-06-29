package com.lvfd.islamic_toolkit_kmp.core.annotations

/**
 * Annotation for Integer Range.
 *
 * This annotation class represents an integer range constraint that can be used to enforce restrictions
 * on integer values. The `IntRange` annotation is typically used to indicate that an integer property or
 * parameter should have a value within the specified range, inclusive of both the `from` and `to` values.
 *
 * @property from The minimum value of the integer range (inclusive).
 * @property to The maximum value of the integer range (inclusive).
 */
annotation class IntRange(val from: Int, val to: Int)


annotation class NeedsTesting(val msg: String = "")