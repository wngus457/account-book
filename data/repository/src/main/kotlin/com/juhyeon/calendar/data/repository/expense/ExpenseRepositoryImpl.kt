package com.juhyeon.calendar.data.repository.expense

import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.expense.Expense
import com.juhyeon.calendar.domain.expense.ExpenseRepository
import com.juhyeon.calendar.domain.expense.month.GetMonthExpenseParam
import com.juhyeon.calendar.domain.mapToResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val expenseLocalDataSource: ExpenseLocalDataSource
) : ExpenseRepository {

    override suspend fun insertExpense(expense: Expense) {
        expenseLocalDataSource.insertExpense(expense.toData())
    }

    override fun getMonthExpenseEntity(param: GetMonthExpenseParam): Flow<Result<List<Expense>>> =
        expenseLocalDataSource.getMonthExpenseEntity(param.year, param.month)
            .map { it.map { item -> item.toDomain() } }
            .mapToResult()
}
