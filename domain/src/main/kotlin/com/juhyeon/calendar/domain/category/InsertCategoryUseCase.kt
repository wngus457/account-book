package com.juhyeon.calendar.domain.category

import com.juhyeon.calendar.domain.FlowUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Category, Unit>(dispatcher) {

    override fun execute(parameters: Category): Flow<Result<Unit>> = flow {
        emit(Result.Success(categoryRepository.insertCategory(parameters)))
    }
}