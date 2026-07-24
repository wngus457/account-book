package com.juhyeon.calendar.data.repository.expense

import com.juhyeon.calendar.domain.expense.Expense

data class ExpenseData(
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

internal fun ExpenseData.toDomain() = Expense(
    key = key,
    year = year,
    month = month,
    date = date,
    expenseList = expenseList.map { it.toDomain() },
    totalExpense = totalExpense,
    totalEarning = totalEarning
)

internal fun Expense.toData() = ExpenseData(
    key = key,
    year = year,
    month = month,
    date = date,
    expenseList = expenseList.map { it.toData() },
    totalExpense = totalExpense,
    totalEarning = totalEarning
)

private fun ExpenseData.ExpenseItem.toDomain() = Expense.ExpenseItem(
    price = price,
    time = time,
    category = category,
    memo = memo,
    isExpenditure = isExpenditure
)

private fun Expense.ExpenseItem.toData() = ExpenseData.ExpenseItem(
    price = price,
    time = time,
    category = category,
    memo = memo,
    isExpenditure = isExpenditure
)