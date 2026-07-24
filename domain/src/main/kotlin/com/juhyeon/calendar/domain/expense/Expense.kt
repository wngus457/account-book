package com.juhyeon.calendar.domain.expense

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
        val price: Long,
        val time: String,
        val category: String,
        val memo: String,
        val isExpenditure: Boolean
    )
}