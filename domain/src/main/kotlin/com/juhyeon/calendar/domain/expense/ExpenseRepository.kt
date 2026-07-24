package com.juhyeon.calendar.domain.expense

import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.expense.month.GetMonthExpenseParam
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    suspend fun insertExpense(expense: Expense)

    fun getMonthExpenseEntity(param: GetMonthExpenseParam): Flow<Result<List<Expense>>>
}