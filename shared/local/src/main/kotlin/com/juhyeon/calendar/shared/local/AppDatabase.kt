package com.juhyeon.calendar.shared.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juhyeon.calendar.shared.local.expense.ExpenseDao
import com.juhyeon.calendar.shared.local.expense.ExpenseEntity

@Database(
    entities = [
        ExpenseEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        private const val ROOM_DATABASE_NAME = "account-book-room"

        fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, ROOM_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}