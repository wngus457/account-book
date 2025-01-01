package com.juhyeon.calendar.shared.local.expense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.juhyeon.calendar.shared.data.expense.ExpenseData
import kotlinx.serialization.Serializable

@Entity(tableName = "expense")
@TypeConverters(ExpenseLocalTypeConverters::class)
data class ExpenseEntity(
    @PrimaryKey
    val key: String = "",
    @ColumnInfo(defaultValue = "")
    val year: String,
    @ColumnInfo(defaultValue = "")
    val month: String,
    @ColumnInfo(defaultValue = "")
    val date: String,
    val expenseList: List<ExpenseItem>
) {
    @Serializable
    data class ExpenseItem(
        val price: Long,
        val time: String,
        val category: String,
        val memo: String,
        val isExpenditure: Boolean
    )
}

internal fun ExpenseEntity.toData() = ExpenseData(
    key = key,
    year = year,
    month = month,
    date = date,
    expenseList = expenseList.map { it.toData() },
    totalExpense = expenseList.filter { it.isExpenditure }.sumOf { it.price },
    totalEarning = expenseList.filter { it.isExpenditure.not() }.sumOf { it.price }
)

internal fun ExpenseEntity.ExpenseItem.toData() = ExpenseData.ExpenseItem(
    price = price,
    time = time,
    category = category,
    memo = memo,
    isExpenditure = isExpenditure
)

internal fun ExpenseData.toLocal() = ExpenseEntity(
    year = year,
    month = month,
    date = date,
    expenseList = expenseList.map { it.toLocal() }
)

internal fun ExpenseData.ExpenseItem.toLocal() = ExpenseEntity.ExpenseItem(
    price = price,
    time = time,
    category = category,
    memo = memo,
    isExpenditure = isExpenditure
)