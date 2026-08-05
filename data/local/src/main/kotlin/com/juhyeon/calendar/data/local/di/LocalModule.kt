package com.juhyeon.calendar.data.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.juhyeon.calendar.data.local.category.CategoryDao
import com.juhyeon.calendar.data.local.category.CategoryLocalDataSourceImpl
import com.juhyeon.calendar.data.local.db.AppDatabase
import com.juhyeon.calendar.data.local.db.dataStore
import com.juhyeon.calendar.data.local.expense.ExpenseDao
import com.juhyeon.calendar.data.local.expense.ExpenseLocalDataSourceImpl
import com.juhyeon.calendar.data.local.setting.SettingLocalDataSourceImpl
import com.juhyeon.calendar.data.repository.category.CategoryLocalDataSource
import com.juhyeon.calendar.data.repository.expense.ExpenseLocalDataSource
import com.juhyeon.calendar.data.repository.setting.SettingLocalDataSource
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

    @Singleton
    @Binds
    abstract fun bindCategoryLocalDataSource(impl: CategoryLocalDataSourceImpl): CategoryLocalDataSource

    @Singleton
    @Binds
    abstract fun bindSettingLocalDataSource(impl: SettingLocalDataSourceImpl): SettingLocalDataSource

    companion object {

        @Singleton
        @Provides
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
            AppDatabase.buildDatabase(context)

        @Singleton
        @Provides
        fun provideExpenseDao(appDatabase: AppDatabase): ExpenseDao =
            appDatabase.expenseDao()

        @Singleton
        @Provides
        fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao =
            appDatabase.categoryDao()

        @Provides
        fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore
    }
}