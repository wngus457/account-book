package com.juhyeon.calendar.data.local.category

import com.juhyeon.calendar.data.repository.category.CategoryData
import com.juhyeon.calendar.data.repository.category.CategoryLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryLocalDataSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryLocalDataSource {

    override suspend fun insertCategory(category: CategoryData) =
        categoryDao.insertCategory(category.toEntity())

    override fun getCategoryList(): Flow<List<CategoryData>> =
        categoryDao.getCategoryEntityList().map { it.map { item -> item.toData() } }
}