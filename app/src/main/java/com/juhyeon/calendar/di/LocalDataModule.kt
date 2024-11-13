package com.juhyeon.calendar.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.juhyeon.calendar.shared.data.expense.ExpenseLocalDataSource
import com.juhyeon.calendar.shared.local.AppDatabase
import com.juhyeon.calendar.shared.local.dataStore
import com.juhyeon.calendar.shared.local.expense.ExpenseDao
import com.juhyeon.calendar.shared.local.expense.ExpenseLocalDataSourceImpl
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
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.buildDatabase(context)

        @Provides
        fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore

        @Singleton
        @Provides
        fun provideExpenseDao(appDatabase: AppDatabase): ExpenseDao = appDatabase.expenseDao()
    }
}