package com.juhyeon.calendar.data.repository.expense

import kotlinx.coroutines.flow.Flow

interface ExpenseLocalDataSource {
    suspend fun insertExpense(data: ExpenseData)
    fun getMonthExpenseEntity(year: String, month: String): Flow<List<ExpenseData>>
}
