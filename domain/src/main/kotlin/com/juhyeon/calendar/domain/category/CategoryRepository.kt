package com.juhyeon.calendar.domain.category

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun deleteAllCategory()
    suspend fun deleteCategory(categoryKey: Int)

    suspend fun insertCategory(category: Category)
    suspend fun insertCategories(categoryList: List<Category>)
    fun getCategoryList(): Flow<List<Category>>
}