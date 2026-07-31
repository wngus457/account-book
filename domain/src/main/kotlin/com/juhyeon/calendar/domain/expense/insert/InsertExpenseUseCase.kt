package com.juhyeon.calendar.domain.expense.insert

import com.juhyeon.calendar.domain.FlowUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.expense.Expense
import com.juhyeon.calendar.domain.expense.ExpenseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Expense, Unit>(dispatcher) {

    override fun execute(parameters: Expense): Flow<Result<Unit>> = flow {
        emit(Result.Success(expenseRepository.insertExpense(parameters)))
    }
}