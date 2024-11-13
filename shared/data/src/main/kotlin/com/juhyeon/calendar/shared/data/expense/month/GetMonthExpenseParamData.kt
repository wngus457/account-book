package com.juhyeon.calendar.shared.data.expense.month

import com.juhyeon.calendar.shared.domain.expense.month.GetMonthExpenseParam

data class GetMonthExpenseParamData(
    val year: String,
    val month: String
)

internal fun GetMonthExpenseParam.toData() = GetMonthExpenseParamData(
    year = year,
    month = month
)