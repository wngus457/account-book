package com.juhyeon.calendar.shared.local.expense

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Query("SELECT * FROM `expense` WHERE expense.year = :year AND expense.month = :month")
    fun getMonthExpenseEntity(year: String, month: String): Flow<List<ExpenseEntity>>
}