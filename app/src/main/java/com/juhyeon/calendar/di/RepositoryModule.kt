package com.juhyeon.calendar.di

import com.juhyeon.calendar.shared.data.expense.ExpenseRepositoryImpl
import com.juhyeon.calendar.shared.domain.expense.ExpenseRepository
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