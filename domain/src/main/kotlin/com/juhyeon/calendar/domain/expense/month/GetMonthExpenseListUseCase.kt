package com.juhyeon.calendar.domain.expense.month

import com.juhyeon.calendar.domain.FlowUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.expense.Expense
import com.juhyeon.calendar.domain.expense.ExpenseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMonthExpenseListUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<GetMonthExpenseParam, List<Expense>>(dispatcher) {

    override fun execute(parameters: GetMonthExpenseParam): Flow<Result<List<Expense>>> {
        return expenseRepository.getMonthExpenseEntity(parameters)
    }
}