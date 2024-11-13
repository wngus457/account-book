package com.juhyeon.calendar.shared.data.expense

import com.juhyeon.calendar.shared.data.expense.month.toData
import com.juhyeon.calendar.shared.domain.Result
import com.juhyeon.calendar.shared.domain.expense.Expense
import com.juhyeon.calendar.shared.domain.expense.ExpenseRepository
import com.juhyeon.calendar.shared.domain.expense.month.GetMonthExpenseParam
import com.juhyeon.calendar.shared.domain.mapToResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val expenseLocalDataSource: ExpenseLocalDataSource
) : ExpenseRepository {

    override suspend fun insertExpense(expense: Expense) =
        expenseLocalDataSource.insertExpense(expense.toData())

    override fun getMonthExpenseEntity(param: GetMonthExpenseParam): Flow<Result<List<Expense>>> =
        expenseLocalDataSource.getMonthExpenseEntity(param.toData()).map { it.map { item -> item.toDomain() } }.mapToResult()
}