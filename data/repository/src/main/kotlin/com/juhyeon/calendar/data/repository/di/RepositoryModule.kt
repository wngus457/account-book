package com.juhyeon.calendar.data.repository.di

import com.juhyeon.calendar.data.repository.category.CategoryRepositoryImpl
import com.juhyeon.calendar.data.repository.expense.ExpenseRepositoryImpl
import com.juhyeon.calendar.data.repository.setting.SettingRepositoryImpl
import com.juhyeon.calendar.domain.category.CategoryRepository
import com.juhyeon.calendar.domain.expense.ExpenseRepository
import com.juhyeon.calendar.domain.setting.SettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindExpenseRepository(repository: ExpenseRepositoryImpl): ExpenseRepository

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository

    @Singleton
    @Binds
    abstract fun bindSettingRepository(repository: SettingRepositoryImpl): SettingRepository
}