package com.juhyeon.calendar.feature.home.data

import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.domain.expense.Expense

/** 하루치 내역. 달력(CalendarBasic)이 Expense를 그대로 받으므로 원본도 함께 들고 있다. */
data class HomeUiModel(
    val expense: Expense,
    val expenseList: List<ExpenseItem>
) {
    data class ExpenseItem(
        val price: Long,
        val time: String,
        val memo: String,
        val isExpenditure: Boolean,
        val category: Category?  // 카테고리 키와 매칭되는 카테고리가 없으면 null
    )
}

internal fun Expense.toUiModel(categoryMap: Map<String, Category>) = HomeUiModel(
    expense = this,
    expenseList = expenseList.map { item ->
        HomeUiModel.ExpenseItem(
            price = item.price,
            time = item.time,
            memo = item.memo,
            isExpenditure = item.isExpenditure,
            category = categoryMap[item.category]
        )
    }
)