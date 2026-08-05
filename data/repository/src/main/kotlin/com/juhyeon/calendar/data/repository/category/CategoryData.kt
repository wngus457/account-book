package com.juhyeon.calendar.data.repository.category

import com.juhyeon.calendar.domain.category.Category

data class CategoryData(
    val categoryKey: String,
    val name: String
)

internal fun CategoryData.toDomain() = Category(
    categoryKey = categoryKey,
    name = name
)

internal fun Category.toData() = CategoryData(
    categoryKey = categoryKey,
    name = name
)