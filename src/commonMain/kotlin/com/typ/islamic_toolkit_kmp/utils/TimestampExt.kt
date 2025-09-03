package com.typ.islamic_toolkit_kmp.utils

import com.typ.islamic_toolkit_kmp.core.datetime.Timestamp

val Long.timestamp: Timestamp get() = Timestamp(this)
