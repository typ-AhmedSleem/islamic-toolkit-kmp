/*
 * This product is developed by TYP Software
 * Project Head : Ahmed Sleem
 * Programmer : Ahmed Sleem
 * Pre-Release Tester : Ahmed Sleem & Ahmed Hafez
 *
 * Copyright (c) TYP Electronics Corporation.  All Rights Reserved
 */
package com.typ.islamic_toolkit_kmp.hijri.lib.ummelqura.text

object HijriCalendarMonthsArabic : HijriCalendarMonthsNames() {

    override val longNames: Map<Int, String>
        get() = mapOf(
            1 to "محرم",
            2 to "صفر",
            3 to "ربيع أول",
            4 to "ربيع ثان",
            5 to "جمادى أول",
            6 to "جمادى ثان",
            7 to "رجب",
            8 to "شعبان",
            9 to "رمضان",
            10 to "شوال",
            11 to "ذو القعدة",
            12 to "ذو الحجة"
        )
    override val shortNames: Map<Int, String>
        get() = mapOf(
            1 to "محرم",
            2 to "صفر",
            3 to "ربيع أول",
            4 to "ربيع ثان",
            5 to "جمادى أول",
            6 to "جمادى ثان",
            7 to "رجب",
            8 to "شعبان",
            9 to "رمضان",
            10 to "شوال",
            11 to "ذو القعدة",
            12 to "ذو الحجة"
        )
}
