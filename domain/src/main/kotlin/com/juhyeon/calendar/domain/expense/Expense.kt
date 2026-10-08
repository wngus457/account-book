package com.juhyeon.calendar.domain.expense

import java.time.LocalDate

data class Expense(
    val key: String,
    val year: String,
    val month: String,
    val date: String,
    val expenseList: List<ExpenseItem>,
    val totalExpense: Long,
    val totalEarning: Long
) {
    data class ExpenseItem(
        val key: String = "",  // 비어 있으면 새 내역
        val price: Long,
        val time: String,
        val category: String,
        val memo: String,
        val isExpenditure: Boolean
    )
}

/** year/month/date는 zero-pad 되지 않은 문자열이라 날짜 비교는 항상 이걸 거친다. */
fun Expense.toLocalDate(): LocalDate = LocalDate.of(year.toInt(), month.toInt(), date.toInt())