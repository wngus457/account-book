package com.juhyeon.calendar.data.local.expense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.juhyeon.calendar.data.local.category.CategoryEntity

@Entity(
    tableName = "expense",
    indices = [Index(value = ["categoryNumber"])],
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["categoryKey"],
            childColumns = ["categoryNumber"],
            onDelete = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val key: Long = 0,
    val year: String,
    val month: String,
    val date: String,
    @ColumnInfo(defaultValue = "")
    val time: String,
    @ColumnInfo(defaultValue = "")
    val memo: String,
    val money: Long,
    @ColumnInfo(name = "categoryNumber", defaultValue = "0")
    val categoryNumber: Int = 0,
    val isPositive: Boolean  // true = 수입, false = 지출
)