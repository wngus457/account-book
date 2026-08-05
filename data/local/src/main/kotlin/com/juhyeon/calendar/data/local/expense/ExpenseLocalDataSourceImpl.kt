package com.juhyeon.calendar.data.local.expense

import com.juhyeon.calendar.data.repository.expense.ExpenseData
import com.juhyeon.calendar.data.repository.expense.ExpenseLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpenseLocalDataSourceImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : ExpenseLocalDataSource {

    override suspend fun insertExpense(data: ExpenseData) {
        //expenseDao.insertExpense(data.toEntity())
        val value = data.expenseList.firstOrNull()
        value?.let { item ->
            val entity = ExpenseEntity(
                year = data.year,
                month = data.month,
                date = data.date,
                time = item.time,
                memo = item.memo,
                money = item.price,
                categoryNumber = item.category.toIntOrNull() ?: 0,
                isPositive = !item.isExpenditure
            )
            expenseDao.insertExpense(entity)
        }
    }

    override fun getMonthExpenseEntity(year: String, month: String): Flow<List<ExpenseData>> =
        expenseDao.getMonthExpenseEntity(year = year, month = month)
            .map { entityList ->
                val groupedByDate = entityList.groupBy { it.date }

                val expenseList = groupedByDate.map { (date, entities) ->
                    val expenseItems = entities.map { entity ->
                        ExpenseData.ExpenseItem(
                            price = entity.money,
                            time = entity.time,
                            category = entity.categoryNumber.toString(),
                            //category = "",
                            memo = entity.memo,
                            isExpenditure = !entity.isPositive
                        )
                    }
                    ExpenseData(
                        key = "${year}-${month}-$date",
                        year = year,
                        month = month,
                        date = date,
                        expenseList = expenseItems,
                        totalExpense = expenseItems.filter { it.isExpenditure }.sumOf { it.price },
                        totalEarning = expenseItems.filter { !it.isExpenditure }.sumOf { it.price }
                    )
                }
                expenseList
            }
}