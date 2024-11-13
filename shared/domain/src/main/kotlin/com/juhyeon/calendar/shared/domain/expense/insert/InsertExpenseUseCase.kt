package com.juhyeon.calendar.shared.domain.expense.insert

import com.juhyeon.calendar.shared.domain.FlowUseCase
import com.juhyeon.calendar.shared.domain.Result
import com.juhyeon.calendar.shared.domain.di.DefaultDispatcher
import com.juhyeon.calendar.shared.domain.expense.Expense
import com.juhyeon.calendar.shared.domain.expense.ExpenseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Expense, Unit>(dispatcher) {
    override fun execute(parameters: Expense): Flow<Result<Unit>> = flow {
        emit(Result.Success(expenseRepository.insertExpense(parameters)))
    }
}