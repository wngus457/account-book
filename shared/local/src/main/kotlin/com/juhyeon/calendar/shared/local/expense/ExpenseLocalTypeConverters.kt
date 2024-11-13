package com.juhyeon.calendar.shared.local.expense

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class ExpenseLocalTypeConverters {

    @TypeConverter
    fun toExpenseItemParam(value: String) = Json.decodeFromString<ExpenseEntity.ExpenseItem>(value)

    @TypeConverter
    fun toExpenseItemParamString(expenseItem: ExpenseEntity.ExpenseItem) = Json.encodeToString(expenseItem)

    @TypeConverter
    fun toExpenseListParam(value: String) = Json.decodeFromString<List<ExpenseEntity.ExpenseItem>>(value)

    @TypeConverter
    fun toExpenseListParamString(expenseList: List<ExpenseEntity.ExpenseItem>) = Json.encodeToString(expenseList)
}