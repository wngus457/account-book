package com.juhyeon.calendar.data.repository.category

import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {

    suspend fun insertCategory(category: CategoryData)
    fun getCategoryList(): Flow<List<CategoryData>>
}