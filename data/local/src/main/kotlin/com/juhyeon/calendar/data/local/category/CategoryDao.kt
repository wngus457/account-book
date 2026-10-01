package com.juhyeon.calendar.data.local.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("DELETE FROM `category`")
    suspend fun deleteAllCategory()

    @Query("DELETE FROM `category` WHERE categoryKey = :categoryKey")
    suspend fun deleteCategory(categoryKey: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM category")
    fun getCategoryEntityList(): Flow<List<CategoryEntity>>
}