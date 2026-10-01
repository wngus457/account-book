package com.juhyeon.calendar.domain.category.delete

import com.juhyeon.calendar.domain.FlowUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.category.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Int, Unit>(dispatcher) {

    override fun execute(parameters: Int): Flow<Result<Unit>> = categoryRepository.getCategoryList()
        .onEach { categoryRepository.deleteCategory(parameters) }
        .map { Result.Success(Unit) }
        .take(1)
}