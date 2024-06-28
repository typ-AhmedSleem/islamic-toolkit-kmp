/*
 * This product is developed by TYP Software
 * Project Head : Ahmed Sleem
 * Programmer : Ahmed Sleem
 * Pre-Release Tester : Ahmed Sleem & Ahmed Hafez
 *
 * Copyright (c) TYP Electronics Corporation.  All Rights Reserved
 */
package com.typ.islamic_toolkit_kmp.hijri.lib.ummelqura.text

object HijriCalendarMonthsEnglish : HijriCalendarMonthsNames() {

    override val longNames: Map<Int, String>
        get() = mapOf(
            1 to "Muharram",
            2 to "Safar",
            3 to "Rabi' al-Awwal",
            4 to "Rabi' al-Thani",
            5 to "Jumada al-Ula",
            6 to "Jumada al-Akhirah",
            7 to "Rajab",
            8 to "Sha'ban",
            9 to "Ramadhan",
            10 to "Shawwal",
            11 to "Thul-Qi'dah",
            12 to "Thul-Hijjah"
        )

    override val shortNames: Map<Int, String>
        get() = mapOf(
            1 to "Muh",
            2 to "Saf",
            3 to "Rab-I",
            4 to "Rab-II",
            5 to "Jum-I",
            6 to "Jum-II",
            7 to "Raj",
            8 to "Sha",
            9 to "Ram",
            10 to "Shw",
            11 to "Thul-Q",
            12 to "Thul-H"
        )
}
