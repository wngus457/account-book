package com.juhyeon.calendar.data.repository.category

import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {

    suspend fun deleteAllCategory()
    suspend fun deleteCategory(categoryKey: Int)
    suspend fun insertCategory(category: CategoryData)
    suspend fun insertCategories(categories: List<CategoryData>)
    fun getCategoryList(): Flow<List<CategoryData>>
}