package com.juhyeon.calendar.shared.util.kotlin.extension

import java.text.DecimalFormat

fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}

fun Int.applyCommaFormat(): String = DecimalFormat("###,###").format(this)