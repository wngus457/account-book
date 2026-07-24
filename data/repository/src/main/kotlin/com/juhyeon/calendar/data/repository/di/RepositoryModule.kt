package com.juhyeon.calendar.data.repository.di

import com.juhyeon.calendar.data.repository.expense.ExpenseRepositoryImpl
import com.juhyeon.calendar.domain.expense.ExpenseRepository
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
}