package com.juhyeon.calendar.data.local.di

import android.content.Context
import com.juhyeon.calendar.data.local.db.AppDatabase
import com.juhyeon.calendar.data.local.expense.ExpenseDao
import com.juhyeon.calendar.data.local.expense.ExpenseLocalDataSourceImpl
import com.juhyeon.calendar.data.repository.expense.ExpenseLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataModule {

    @Singleton
    @Binds
    abstract fun bindExpenseLocalDataSource(impl: ExpenseLocalDataSourceImpl): ExpenseLocalDataSource

    companion object {

        @Singleton
        @Provides
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
            AppDatabase.buildDatabase(context)

        @Singleton
        @Provides
        fun provideExpenseDao(appDatabase: AppDatabase): ExpenseDao =
            appDatabase.expenseDao()
    }
}