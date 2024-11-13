package com.juhyeon.calendar.shared.local.expense

import com.juhyeon.calendar.shared.data.expense.ExpenseData
import com.juhyeon.calendar.shared.data.expense.ExpenseLocalDataSource
import com.juhyeon.calendar.shared.data.expense.month.GetMonthExpenseParamData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpenseLocalDataSourceImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : ExpenseLocalDataSource {

    override suspend fun insertExpense(expenseData: ExpenseData) = expenseDao.insertExpense(expenseData.toLocal())
    override fun getMonthExpenseEntity(param: GetMonthExpenseParamData): Flow<List<ExpenseData>> =
        expenseDao.getMonthExpenseEntity(year = param.year, month = param.month).map { it.map { it.toData() } }

}