package com.juhyeon.calendar.data.local.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category")
    fun getCategoryEntityList(): Flow<List<CategoryEntity>>
}