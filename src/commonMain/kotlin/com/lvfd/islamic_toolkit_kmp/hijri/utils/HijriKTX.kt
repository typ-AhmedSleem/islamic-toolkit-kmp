package com.lvfd.islamic_toolkit_kmp.hijri.utils

import com.lvfd.islamic_toolkit_kmp.core.datetime.Timestamp
import com.lvfd.islamic_toolkit_kmp.hijri.lib.HijriCalendar

fun Timestamp.toHijri() = HijriCalendar.toHijri(this)