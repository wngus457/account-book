package com.juhyeon.calendar.domain.category

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun insertCategory(category: Category)
    fun getCategoryList(): Flow<List<Category>>
}