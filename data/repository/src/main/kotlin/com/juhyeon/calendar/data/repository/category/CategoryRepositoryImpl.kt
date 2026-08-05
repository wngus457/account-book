package com.juhyeon.calendar.data.repository.category

import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.domain.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryLocalDataSource: CategoryLocalDataSource
) : CategoryRepository {

    override suspend fun insertCategory(category: Category) =
        categoryLocalDataSource.insertCategory(category.toData())

    override fun getCategoryList(): Flow<List<Category>> =
        categoryLocalDataSource.getCategoryList().map { it.map { item -> item.toDomain() } }
}