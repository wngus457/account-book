package com.juhyeon.calendar.shared.domain.expense.month

import com.juhyeon.calendar.shared.domain.FlowUseCase
import com.juhyeon.calendar.shared.domain.Result
import com.juhyeon.calendar.shared.domain.di.DefaultDispatcher
import com.juhyeon.calendar.shared.domain.expense.Expense
import com.juhyeon.calendar.shared.domain.expense.ExpenseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMonthExpenseListUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<GetMonthExpenseParam, List<Expense>>(dispatcher) {

    override fun execute(parameters: GetMonthExpenseParam): Flow<Result<List<Expense>>> {
        return expenseRepository.getMonthExpenseEntity(parameters)
    }
}