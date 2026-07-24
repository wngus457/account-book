package com.juhyeon.calendar.shared.local.expense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.juhyeon.calendar.shared.data.expense.ExpenseData
import com.juhyeon.calendar.shared.local.category.CategoryEntity

@Entity(
    tableName = "expense",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["categoryKey"],
            childColumns = ["categoryNumber"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val key: Long,
    @ColumnInfo(defaultValue = "")
    val time: String,
    @ColumnInfo(defaultValue = "")
    val memo: String,
    val money: Long,
    @ColumnInfo(name = "categoryNumber")
    val categoryNumber: Int,
    val isPositive: Boolean
)

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