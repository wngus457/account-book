package com.juhyeon.calendar.shared.util.kotlin.extension

import java.text.DecimalFormat

fun Long.applyCommaFormat(): String = DecimalFormat("###,###").format(this)