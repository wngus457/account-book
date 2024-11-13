package com.juhyeon.calendar.shared.data.expense

import com.juhyeon.calendar.shared.data.expense.month.GetMonthExpenseParamData
import kotlinx.coroutines.flow.Flow

interface ExpenseLocalDataSource {
    suspend fun insertExpense(expenseData: ExpenseData)

    fun getMonthExpenseEntity(param: GetMonthExpenseParamData): Flow<List<ExpenseData>>
}