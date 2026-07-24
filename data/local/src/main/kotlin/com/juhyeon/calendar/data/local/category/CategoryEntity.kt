package com.juhyeon.calendar.data.local.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val categoryKey: Int = 0,
    @ColumnInfo(name = "name")
    val name: String
)