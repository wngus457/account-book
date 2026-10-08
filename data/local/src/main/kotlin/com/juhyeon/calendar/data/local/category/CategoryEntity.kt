package com.juhyeon.calendar.data.local.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juhyeon.calendar.data.repository.category.CategoryData

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoryKey")
    val categoryKey: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "icon")
    val icon: String
)

internal fun CategoryEntity.toData() = CategoryData(
    categoryKey = categoryKey.toString(),
    name = name,
    icon = icon
)

internal fun CategoryData.toEntity() = CategoryEntity(
    name = name,
    icon = icon
)